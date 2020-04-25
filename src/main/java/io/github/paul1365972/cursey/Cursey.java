package io.github.paul1365972.cursey;

import io.github.paul1365972.cursey.curse.CurseManager;
import io.github.paul1365972.cursey.state.PlayerDataManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Cursey extends JavaPlugin implements CommandExecutor {
	
	private CurseManager curseManager;
	private PlayerDataManager playerDataManager;
	
	@Override
	public void onEnable() {
		CurseyCommand curseyCommandHandler = new CurseyCommand(this);
		PluginCommand curseyCommand = getCommand("cursey");
		curseyCommand.setExecutor(curseyCommandHandler);
		curseyCommand.setTabCompleter(curseyCommandHandler);
		
		CurseyListener curseyListener = new CurseyListener(this);
		getServer().getPluginManager().registerEvents(curseyListener, this);
		
		playerDataManager = new PlayerDataManager(this);
		
		curseManager = new CurseManager(this);
		curseManager.init();
		getServer().getScheduler().runTaskTimer(this, curseManager::tick, 1, 1);
	}
	
	@Override
	public void onDisable() {
		curseManager.shutdown();
		playerDataManager.shutdown();
	}
	
	public CurseManager getCurseManager() {
		return curseManager;
	}
	
	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}
	
}
