package com.baldricnetwork.bansystem.infraction.tasks;

import org.bukkit.Bukkit;

import com.baldricnetwork.bansystem.infraction.InfractionTask;

public class MuteInfractionTask extends InfractionTask {
 
	public MuteInfractionTask(int time) {
		super(time); 
	}

	@Override
	public void run() {
		 int time = this.getTime();
		 
		 if(time != 0){
			 time--;
		 }else{
			 end();
			 Bukkit.getScheduler().cancelTask(getCountdown());
		 }
	} 

	@Override
	public void end() {
		
	} 
}
