package io.github.paul1365972.cursey.state;

import io.github.paul1365972.cursey.Cursey;
import io.github.paul1365972.cursey.curse.Curse;
import io.github.paul1365972.cursey.curse.CurseDeserializer;

import java.util.HashMap;
import java.util.Map;

public class CurseRegistry {
	
	private final Cursey cursey;
	
	private final Map<String, CurseDeserializer<?>> curseDeserializer = new HashMap<>();
	
	public CurseRegistry(Cursey cursey) {
		this.cursey = cursey;
	}
	
	public <T extends Curse> void registerDeserializer(Class<T> clazz, CurseDeserializer<T> deserializer) {
		curseDeserializer.put(clazz.getName().toLowerCase(), deserializer);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Curse> CurseDeserializer<T> getDeserializer(String typeName) {
		return (CurseDeserializer<T>) curseDeserializer.get(typeName.toLowerCase());
	}
	
}