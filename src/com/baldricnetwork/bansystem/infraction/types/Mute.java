package com.baldricnetwork.bansystem.infraction.types;

import org.bukkit.entity.Player;

import com.baldricnetwork.bansystem.infraction.Infraction;
import com.baldricnetwork.bansystem.infraction.InfractionManager;
import com.baldricnetwork.bansystem.infraction.tasks.MuteInfractionTask;

public class Mute extends Infraction {

	public Mute(Player by, Player who, MuteInfractionTask task, String reason) {
		super(by, who, task, reason);
	}


	@Override
	public String message() {
		return "You have been muted by " + getBy().getName() + " for " + getReason();
	}

	@Override
	public void execute() {
		getRuleBreaker().sendMessage(message());
		InfractionManager.getInfractionManager().addMutedPlayer(this.getRuleBreaker(), this);
	}

	@Override
	public String getName() {
		return null;
	}
}