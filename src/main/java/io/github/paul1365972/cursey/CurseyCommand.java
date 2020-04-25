package io.github.paul1365972.cursey;

import io.github.paul1365972.cursey.state.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CurseyCommand implements TabExecutor {
	
	private final List<String> validArguments = Arrays.asList("enable", "disable", "toggle");
	
	private final Cursey cursey;
	
	public CurseyCommand(Cursey cursey) {
		this.cursey = cursey;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			PlayerData data = cursey.getPlayerDataManager().get(player);
			if (args.length == 0) {
				player.sendMessage("The curse is" + (data.getCurseEnabled() ? " enabled" : " disabled") + " for you");
			} else if (args.length == 1) {
				if ("enable".equalsIgnoreCase(args[0])) {
					data.setCurseEnabled(true);
				} else if ("disable".equalsIgnoreCase(args[0])) {
					data.setCurseEnabled(false);
				} else if ("toggle".equalsIgnoreCase(args[0])) {
					data.setCurseEnabled(!data.getCurseEnabled());
				} else {
					return false;
				}
				player.sendMessage("The curse is now" + (data.getCurseEnabled() ? " enabled" : " disabled") + " for you");
			} else {
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return validArguments;
		return null;
	}
}
