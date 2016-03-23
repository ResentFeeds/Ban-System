package com.baldricnetwork.bansystem.infraction;

import org.bukkit.Bukkit;

import com.baldricnetwork.bansystem.BanSystem;

public abstract class InfractionTask implements Runnable {

	private int time;
	private int count; 

	public InfractionTask(int time) {
		this.time = time;
		
		// idk why im using a repeating task 
		this.count = Bukkit.getScheduler().scheduleAsyncRepeatingTask(BanSystem.getInstance(), this, 0, 20);
	}  
	
	public abstract void end();
	
	
	public int getTime(){
		return this.time;
	}
	
	
	public int getCountdown(){
		return this.count;
	}
}
