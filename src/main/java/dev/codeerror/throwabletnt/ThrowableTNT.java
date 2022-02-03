package dev.codeerror.throwabletnt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.codeerror.throwabletnt.listeners.ThrowTNT;

public class ThrowableTNT extends JavaPlugin implements CommandExecutor {
	
	private static final String chatPrefix = ChatColor.RED + "[" + ChatColor.WHITE + "ThrowableTNT" + ChatColor.RED + "] ";
	public static boolean hasFactions = false;

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("Factions")) {
			hasFactions = true;
			this.getLogger().info("Factions plugin detected! Anti-Grief safeguards enabled!");
		}

		Bukkit.getPluginManager().registerEvents(new ThrowTNT(), this);
		this.getCommand("throwabletnt").setExecutor(this);
		
		this.getLogger().info("ThrowableTNT v1.4.2 has been successfully enabled! Created by CodeError.");

	}

	@Override
	public void onDisable() {
		this.getLogger().info("ThrowableTNT v1.4.2 has been disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 0) {

			if (!(sender instanceof Player)) return false;
			
			sender.sendMessage(chatPrefix + ChatColor.GRAY + "This plugin makes Minecraft TNT a throwable object! " + ChatColor.GREEN + "ThrowableTNT v1.4.2 - Created by CodeError");
			return true;

		} else {

			if (args[0].equalsIgnoreCase("reload")) {

				if (!(sender instanceof Entity)) {

					this.reloadConfig();
					this.getConfig();
					this.getLogger().info("Plugin configuration has been successfully reloaded. All changes have been applied.");

					return true;

				} else if (sender instanceof Player && sender.hasPermission("throwabletnt.reload")) {

					this.reloadConfig();
					this.getConfig();
					this.getLogger().info("Plugin configuration has been successfully reloaded. All changes have been applied.");

					sender.sendMessage(chatPrefix + ChatColor.GREEN + "Plugin configuration has been successfully reloaded. All changes have been applied.");
					return true;

				}

				sender.sendMessage(chatPrefix + ChatColor.DARK_RED + "You do not have the appropriate permissions to perform this command.");
				return true;

			}

		}
		
		return false;

	}

}
