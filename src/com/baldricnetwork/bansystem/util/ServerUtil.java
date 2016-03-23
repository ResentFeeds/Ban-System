package com.baldricnetwork.bansystem.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtil { 

	public static boolean isSelf(Player sender, Player target) {
		if (sender.getUniqueId().equals(target.getUniqueId())) {
			return true;
		}
		return false;
	}
}
