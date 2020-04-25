package io.github.paul1365972.cursey.state;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersistentDataHelper {
	
	private static final Map<Class<?>, PersistentDataType<?, ?>> PRIMITIVE_TYPES = Collections.unmodifiableMap(Stream.of(
			PersistentDataType.BYTE, PersistentDataType.SHORT, PersistentDataType.INTEGER, PersistentDataType.LONG,
			PersistentDataType.BYTE_ARRAY, PersistentDataType.INTEGER_ARRAY, PersistentDataType.LONG_ARRAY,
			PersistentDataType.FLOAT, PersistentDataType.DOUBLE,
			PersistentDataType.STRING, PersistentDataType.TAG_CONTAINER
	).collect(Collectors.toMap(PersistentDataType::getComplexType, Function.identity())));
	
	public static final PersistentDataType<byte[], UUID> UUID = new PersistentDataType<byte[], UUID>() {
		@NotNull
		@Override
		public Class<byte[]> getPrimitiveType() {
			return byte[].class;
		}
		
		@NotNull
		@Override
		public Class<UUID> getComplexType() {
			return UUID.class;
		}
		
		@NotNull
		@Override
		public byte[] toPrimitive(@NotNull UUID complex, @NotNull PersistentDataAdapterContext context) {
			ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
			bb.putLong(complex.getMostSignificantBits());
			bb.putLong(complex.getLeastSignificantBits());
			return bb.array();
		}
		
		@NotNull
		@Override
		public java.util.UUID fromPrimitive(@NotNull byte[] primitive, @NotNull PersistentDataAdapterContext context) {
			ByteBuffer bb = ByteBuffer.wrap(primitive);
			long firstLong = bb.getLong();
			long secondLong = bb.getLong();
			return new UUID(firstLong, secondLong);
		}
	};
	
	
	@SuppressWarnings("unchecked")
	public static  <T> PersistentDataType<T, T> getPrimitivePersistentDataType(Class<T> type) {
		return (PersistentDataType<T, T>) PRIMITIVE_TYPES.get(type);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void set(PersistentDataContainer container, NamespacedKey key, T value) {
		PersistentDataType<T, T> type = getPrimitivePersistentDataType((Class<T>) value.getClass());
		container.set(key, type, value);
	}
	
	public static void setAll(PersistentDataContainer container, JavaPlugin plugin, Map<String, Object> tags) {
		tags.forEach((key, value) -> set(container, new NamespacedKey(plugin, key), value));
	}
}
