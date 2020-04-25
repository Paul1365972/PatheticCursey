package io.github.paul1365972.cursey.curse;

import io.github.paul1365972.cursey.Cursey;
import io.github.paul1365972.cursey.DeeperWorldInterface;
import io.github.paul1365972.cursey.state.PlayerData;
import org.bukkit.entity.Player;

public class CurseManager {
	
	private static final double EPSILON = 1 / 1_000_000.0;
	
	private static final double ascensionThreshold = 10; //TODO Config
	
	private final Cursey cursey;
	
	public CurseManager(Cursey cursey) {
		this.cursey = cursey;
	}
	
	public void tick() {
		for (Player player : cursey.getServer().getOnlinePlayers()) {
			PlayerData data = cursey.getPlayerDataManager().get(player);
			double deltaY = DeeperWorldInterface.getTrueHeight(player.getLocation()) - data.getLastHeight();
			data.setAscended(data.getAscended() + deltaY);
			if (data.getAscended() >= ascensionThreshold - EPSILON) {
				data.setAscended(data.getAscended() - ascensionThreshold);
				triggerCurse(player, data);
			}
		}
	}
	
	public void triggerCurse(Player entity, PlayerData data) {
	}
	
	public void onPlayerJoin(Player entity) {
		// Make sure last Height is reset in case plugin was disabled before
		cursey.getPlayerDataManager().get(entity).setLastHeight(DeeperWorldInterface.getTrueHeight(entity.getLocation()));
	}
	
	public void onPlayerQuit(Player entity) {
	
	}
	
	public void onPlayerDie(Player entity) {
	
	}
	
	public void init() {
		cursey.getServer().getOnlinePlayers().forEach(this::onPlayerJoin);
	}
	
	public void shutdown() {
		cursey.getServer().getOnlinePlayers().forEach(this::onPlayerQuit);
	}
	
}
