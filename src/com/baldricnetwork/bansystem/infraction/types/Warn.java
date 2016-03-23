package com.baldricnetwork.bansystem.infraction.types;

import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

import com.baldricnetwork.bansystem.infraction.Infraction;
import com.baldricnetwork.bansystem.infraction.InfractionManager;

public class Warn extends Infraction {

	public Warn(Player by, Player who, String reason) {
		super(by, who, reason);
	}

	@Override
	public void execute() {
		getRuleBreaker().sendMessage("");
		getRuleBreaker().sendMessage(message());
		getRuleBreaker().sendMessage("");
		InfractionManager.getInfractionManager().addInfraction(this, getRuleBreaker());
	}

	@Override
	public String message() {
		return GOLD + getRuleBreaker().getName() + DARK_AQUA + " has been warned by " + GOLD + getBy().getName()
				+ DARK_AQUA + " for " + GOLD + getReason();
	}
	
	
	@Override
	public String getName(){
		return "warn";
	}
}
