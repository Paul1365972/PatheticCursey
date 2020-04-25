package io.github.paul1365972.cursey;

import org.bukkit.Location;

public class DeeperWorldInterface {
	
	//TODO This needs proper implementation via DeeperWorld
	
	public static double getTrueHeight(Location location) {
		return location.getY();
	}
	
	public static int getLayer(Location location) {
		return 0;
	}
	
	public static int getSection(Location location) {
		return 1;
	}
	
	public static double getCurseStrength(Location location) {
		return 1.0;
	}
	
}
