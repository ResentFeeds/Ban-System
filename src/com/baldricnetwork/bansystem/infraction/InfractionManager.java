package com.baldricnetwork.bansystem.infraction;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.baldricnetwork.bansystem.infraction.types.Mute;

public class InfractionManager {

	/** instance of the this class */
	private static InfractionManager instance;

	// may change this
	private HashMap<UUID, Mute> mutedPlayers = new HashMap<>();

	private HashMap<Player, List<Infraction>> infractionsForPlayer = new HashMap<>();

	public InfractionManager() {
		instance = this;
	}

	public static InfractionManager getInfractionManager() {
		return instance;
	}

	public boolean isMuted(Player searched) {
		return mutedPlayers.containsKey(searched.getUniqueId());
	}

	public void addInfraction(Infraction brokenRule, Player target) {
		getInfractions(target).add(brokenRule);
	}

	public boolean hasInfractions(Player p) {
		return this.infractionsForPlayer.get(p).size() != 0;
	}

	public List<Infraction> getInfractions(Player p) {
		return infractionsForPlayer.get(p);
	}

	public HashMap<Player, List<Infraction>> getInfractionsForPlayer() {
		return infractionsForPlayer;
	}

	public HashMap<UUID, Mute> getMuted() {
		return mutedPlayers;
	}

	public void addMutedPlayer(Player ruleBreaker, Mute mute) {
		this.mutedPlayers.put(ruleBreaker.getUniqueId(), mute);
	} 
}
