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

	@Override
	public void onEnable() {

		this.saveDefaultConfig();

		Bukkit.getPluginManager().registerEvents(new ThrowTNT(), this);
		this.getCommand("throwabletnt").setExecutor(this);
		
		if (Bukkit.getServer().getPluginManager().getPlugin("Factions") != null) {
			
			this.getLogger().info("Factions plugin has been detected!");
			this.getLogger().info("Hooking into Factions API...");
			this.getLogger().info("Successfully hooked into Factions API! Factions Anti-Grief has been enabled!");
			
		}
		
		this.getLogger().info("ThrowableTNT v1.4.1 has been successfully enabled! Created by CodeError.");

	}

	@Override
	public void onDisable() {

		if (Bukkit.getServer().getPluginManager().getPlugin("Factions") != null) {
			
			this.getLogger().info("Unhooking from Factions API...");
			this.getLogger().info("Successfully unhooked from Factions API.");
			
		}
		
		this.getLogger().info("ThrowableTNT v1.4.1 has been disabled.");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 0) {

			if (!(sender instanceof Player)) {
				
				return false;
				
			}
			
			sender.sendMessage(chatPrefix + ChatColor.GRAY + "This plugin makes Minecraft TNT a throwable object! " + ChatColor.GREEN + "ThrowableTNT v1.4.1 - Created by CodeError");

			return true;

		}

		else {

			if (args[0].equalsIgnoreCase("reload")) {

				if (!(sender instanceof Entity)) {
					
					this.reloadConfig();
					this.getConfig();
					this.getLogger().info("Plugin configuration has been successfully reloaded. All changes have been applied.");

					return true;

				}

				else if (sender instanceof Player && sender.hasPermission("throwabletnt.reload")) {

					this.reloadConfig();
					this.getConfig();
					this.getLogger().info("Plugin configuration has been successfully reloaded. All changes have been applied.");
					sender.sendMessage(chatPrefix + ChatColor.GREEN + "Plugin configuration has been successfully reloaded. All changes have been applied.");

					return true;

				}
				
				else {
					
					sender.sendMessage(chatPrefix + ChatColor.DARK_RED + "You do not have the appropriate permissions to perform this command.");
					
					return true;
					
				}

			}

		}
		
		return false;

	}

}
