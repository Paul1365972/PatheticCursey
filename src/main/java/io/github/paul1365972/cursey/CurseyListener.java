package io.github.paul1365972.cursey;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CurseyListener implements Listener {
	
	private final List<PlayerTeleportEvent.TeleportCause> ignoredTeleportCauses = Arrays.asList(
			//PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT,
			//PlayerTeleportEvent.TeleportCause.ENDER_PEARL,
	);
	
	private final Cursey cursey;
	
	public CurseyListener(Cursey cursey) {
		this.cursey = cursey;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onTeleport(PlayerTeleportEvent event) {
		Objects.requireNonNull(event.getTo());
		if (!ignoredTeleportCauses.contains(event.getCause())) {
			cursey.getPlayerDataManager().get(event.getPlayer()).setLastHeight(DeeperWorldInterface.getTrueHeight(event.getTo()));
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onRespawn(PlayerRespawnEvent event) {
		cursey.getPlayerDataManager().get(event.getPlayer()).setLastHeight(DeeperWorldInterface.getTrueHeight(event.getRespawnLocation()));
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent event) {
		cursey.getCurseManager().onPlayerJoin(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onQuit(PlayerQuitEvent event) {
		cursey.getCurseManager().onPlayerQuit(event.getPlayer());
		cursey.getPlayerDataManager().save(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onDie(PlayerDeathEvent event) {
		cursey.getCurseManager().onPlayerDie(event.getEntity());
	}
	
}
