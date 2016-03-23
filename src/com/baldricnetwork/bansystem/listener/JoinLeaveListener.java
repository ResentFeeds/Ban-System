package com.baldricnetwork.bansystem.listener;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.baldricnetwork.bansystem.infraction.Infraction;
import com.baldricnetwork.bansystem.infraction.InfractionManager;

public class JoinLeaveListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPlayedBefore()) {
			InfractionManager.getInfractionManager().getInfractionsForPlayer().put(event.getPlayer(),
					new ArrayList<Infraction>());
		} 
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		// TODO
	}
}
