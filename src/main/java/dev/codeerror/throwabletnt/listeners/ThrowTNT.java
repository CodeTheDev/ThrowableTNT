package dev.codeerror.throwabletnt.listeners;

import dev.codeerror.throwabletnt.ThrowableTNT;
import dev.codeerror.throwabletnt.util.CParticles;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ThrowTNT implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		World world = player.getWorld();
		ItemStack item = player.getInventory().getItemInMainHand();

		if ((player.hasPermission("throwabletnt.throw") && event.getAction() == Action.RIGHT_CLICK_AIR && (player.isSneaking() || player.isInsideVehicle()) && item.getType() == Material.TNT)) {

			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (item.getAmount() == 1) {
					player.getInventory().remove(item);
				} else if (item.getAmount() > 1) {
					item.setAmount(item.getAmount() - 1);
				}
			}

			TNTPrimed tnt = world.spawn(player.getLocation().add(0.0, 1, 0.0), TNTPrimed.class);
			Vector playerDirection = player.getLocation().getDirection();

			double throwingPower = ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getDouble("throwing-power", 2.5);
			int explosivePower = ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getInt("explosive-power", 6);
			boolean incendiary = ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getBoolean("incendiary", false);
			boolean glow = ThrowableTNT.getPlugin(ThrowableTNT.class).getConfig().getBoolean("glow", false);

			tnt.setVelocity(playerDirection.multiply(throwingPower + 0D));
			tnt.setYield(explosivePower + 0f);
			tnt.setIsIncendiary(incendiary);
			tnt.setGlowing(glow);
			
			new CParticles(player, tnt).startHelix();

		}

	}

}
