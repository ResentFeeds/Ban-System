package com.baldricnetwork.bansystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.baldricnetwork.bansystem.commands.AdminCommands;
import com.baldricnetwork.bansystem.infraction.InfractionManager;
import com.baldricnetwork.bansystem.listener.JoinLeaveListener;
import com.baldricnetwork.bansystem.listener.MuteListener;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public class BanSystem extends JavaPlugin{
	
	// make a way to use MySQL with a database

	private static BanSystem instance;

	private CommandsManager<CommandSender> commands;

	@Override
	public void onEnable() {
		instance = this; 
		
		new InfractionManager();
		
		setupCommands();  
		
		loadListener(new JoinLeaveListener());
		loadListener(new MuteListener());
	}

	private void setupCommands() {
		this.commands = new CommandsManager<CommandSender>() {
			@Override
			public boolean hasPermission(CommandSender sender, String perm) {
				return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
			}
		};

		CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
		cmdRegister.register(AdminCommands.class);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			this.commands.execute(cmd.getName(), args, sender, sender);
		} catch (CommandPermissionsException e) {
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
		} catch (MissingNestedCommandException e) {
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (CommandUsageException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (WrappedCommandException e) {
			if (e.getCause() instanceof NumberFormatException) {
				sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
			} else {
				sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
				e.printStackTrace();
			}
		} catch (CommandException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
		} catch (com.sk89q.minecraft.util.commands.CommandException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void loadListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static BanSystem getInstance() {
		return instance;
	}
}
