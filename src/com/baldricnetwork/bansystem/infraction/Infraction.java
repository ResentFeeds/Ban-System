package com.baldricnetwork.bansystem.infraction;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

public abstract class Infraction {
 
	private final Player by;
	private final Player who;
	private final String reason;
	private final InfractionTask task;
	
	public Infraction(Player by, @Nullable Player who, @Nullable InfractionTask task, String reason){
		 this.by = by;
		 this.who = who;
		 this.task = task;
		 this.reason = reason;
	}
	
	
	public Infraction(Player by, Player who, String reason){
		this(by, who, null, reason);	
	}
	
	public Player getBy(){
		return this.by;
	}
	
	
	public InfractionTask getTask(){
		return this.task;
	}
	
	public boolean hasRuleBreaker(){
		return this.who != null;
	}
	
	public Player getRuleBreaker(){
		return this.who;
	}
	
	public String getReason(){
		return this.reason;
	}
	
	
	// the message sent to the player (if its a warn or mute)
	public abstract String message();
	
	// execute this method when a staff member bans or kicks a player :)
	public abstract void execute();

	// the name of the infraction(s) used when they view the players 
	public abstract String getName();
}
