package dev.codeerror.throwabletnt.util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;

public class ParticleManager {

	private static final HashMap<UUID, Integer> activeParticles = new HashMap<>();
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
		return activeParticles.containsKey(uuid);
	}
	
	public void removeID() {
		activeParticles.remove(uuid);
	}
	
	public void endTask() {
		Bukkit.getScheduler().cancelTask(getID());
	}

}
