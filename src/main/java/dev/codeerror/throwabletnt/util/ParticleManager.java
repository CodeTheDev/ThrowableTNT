package dev.codeerror.throwabletnt.util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;

public class ParticleManager {

	private static HashMap<UUID, Integer> activeParticles = new HashMap<UUID, Integer>();
	private final UUID uuid;

	public ParticleManager(UUID uuid) {

		this.uuid = uuid;

	}

	public void setID(int id) {

		activeParticles.put(uuid, id);

	}

	public int getID() {

		return activeParticles.get(uuid);

	}

	public boolean hasID() {

		if (activeParticles.containsKey(uuid)) {

			return true;

		}

		return false;

	}
	
	public void removeID() {
		
		activeParticles.remove(uuid);
		
	}
	
	public void endTask() {
		
		Bukkit.getScheduler().cancelTask(getID());
		
	}

}
