package com.baldricnetwork.bansystem.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.baldricnetwork.bansystem.infraction.InfractionManager;
import com.baldricnetwork.bansystem.infraction.types.Mute;

public class MuteListener implements Listener {

	@EventHandler
	public void onMuteChat(AsyncPlayerChatEvent event){
		InfractionManager infractionManager = InfractionManager.getInfractionManager();
		if(infractionManager.isMuted(event.getPlayer())){
			Mute muteInfraction = infractionManager.getMuted().get(event.getPlayer().getUniqueId());
			event.setCancelled(true); 
			event.getPlayer().sendMessage(muteInfraction.message());
		}
	}
}