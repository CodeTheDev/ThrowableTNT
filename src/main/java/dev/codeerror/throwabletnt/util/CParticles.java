package dev.codeerror.throwabletnt.util;

import dev.codeerror.throwabletnt.ThrowableTNT;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

public class CParticles {

	private int task;
	private final Player player;
	private final TNTPrimed tnt;

	public CParticles(Player player, TNTPrimed tnt) {

		this.player = player;
		this.tnt = tnt;

	}

	public void startHelix() {

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(ThrowableTNT.getPlugin(ThrowableTNT.class), new Runnable() {

			double myPain;
			Location helix1, helix2;
			ParticleManager particleManager = new ParticleManager(player.getUniqueId());

			@Override
			public void run() {

				if (ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getBoolean("particles", true)) {

					if (!particleManager.hasID()) { 

						particleManager.setID(task);

					}

					if (tnt.isDead()) {

						particleManager.endTask();
						particleManager.removeID();

					}

					myPain += Math.PI / 16;

					helix1 = tnt.getLocation().clone().add(Math.cos(myPain), Math.sin(myPain) + 0.5, Math.sin(myPain));
					helix2 = tnt.getLocation().clone().add(Math.cos(myPain + Math.PI), Math.sin(myPain) + 0.5, Math.sin(myPain + Math.PI));

					player.getWorld().spawnParticle(Particle.FLAME, helix1, 0);
					player.getWorld().spawnParticle(Particle.FLAME, helix2, 0);

				}
				
				if (Bukkit.getServer().getPluginManager().isPluginEnabled("Factions")) {
					
					FLocation fLoc = new FLocation(tnt.getLocation());
					FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
					
					Faction playerFac = fPlayer.getFaction();
					Faction tntFac = Board.getInstance().getFactionAt(fLoc);
					
					int explosivePower = ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getInt("explosive-power", 6);
					
					if (playerFac.getRelationWish(tntFac).isNeutral() || playerFac.getRelationWish(tntFac).isAlly() || playerFac.getRelationWish(tntFac).isTruce() || tntFac.isSafeZone()) {
						
						tnt.setYield(0f);
						
					}
					
					else {
						
						tnt.setYield(explosivePower + 0f);
						
					}
					
				}
				
				if (ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getBoolean("explode-on-contact", true)) {
					
					if (tnt.isOnGround()) {

						tnt.setFuseTicks(0);

					}
					
				}

			}

		}, 0, 1);

	}

}
