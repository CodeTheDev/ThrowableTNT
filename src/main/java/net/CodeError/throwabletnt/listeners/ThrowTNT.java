package net.CodeError.throwabletnt.listeners;

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

import net.CodeError.throwabletnt.Main;
import net.CodeError.throwabletnt.util.CParticles;

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

				}

				else if (item.getAmount() > 1) {

					item.setAmount(item.getAmount() - 1);

				}

			}

			TNTPrimed tnt = (TNTPrimed) world.spawn(player.getLocation().add(0.0, 1, 0.0), TNTPrimed.class);
			Vector playerDirection = player.getLocation().getDirection();
			double throwingPower = Main.getPlugin(Main.class).getConfig().getDouble("throwing-power", 2.5);
			int explosivePower = Main.getPlugin(Main.class).getConfig().getInt("explosive-power", 6);
			boolean incendiary = Main.getPlugin(Main.class).getConfig().getBoolean("incendiary", false);
			boolean glow = Main.getPlugin(Main.class).getConfig().getBoolean("glow", false);

			tnt.setVelocity(playerDirection.multiply(throwingPower + 0D));
			tnt.setYield(explosivePower + 0f);
			tnt.setIsIncendiary(incendiary);
			tnt.setGlowing(glow);
			
			new CParticles(player, tnt).startHelix();

		}

	}

}
