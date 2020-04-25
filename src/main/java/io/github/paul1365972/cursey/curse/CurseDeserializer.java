package io.github.paul1365972.cursey.curse;

import io.github.paul1365972.cursey.Cursey;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@FunctionalInterface
public interface CurseDeserializer<R> {
	
	R deserialize(Cursey cursey, UUID uuid, Player player, Map<String, Object> data);
	
}
