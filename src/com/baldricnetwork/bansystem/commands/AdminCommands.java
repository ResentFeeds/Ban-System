package com.baldricnetwork.bansystem.commands;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.baldricnetwork.bansystem.infraction.Infraction;
import com.baldricnetwork.bansystem.infraction.InfractionManager;
import com.baldricnetwork.bansystem.infraction.tasks.MuteInfractionTask;
import com.baldricnetwork.bansystem.infraction.types.Mute;
import com.baldricnetwork.bansystem.infraction.types.Warn;
import com.baldricnetwork.bansystem.util.Characters;
import com.baldricnetwork.bansystem.util.TimeUtils;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;

public class AdminCommands {

	@Command(aliases = {"mute" }, desc = "mute a player for a certain reason", usage = "[player] [time] [reason]", min = 3, max = 3)
	public static void mute(final CommandContext args, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			Player who = Bukkit.getPlayer(args.getString(0));

			if (who == null) {
				player.sendMessage(RED + "No players matched query");
				return;
			}

			int time = TimeUtils.parseTime("5m");

			if (args.getString(1) != null) {
				time = TimeUtils.parseTime(args.getString(1));
			}

			String reason = " accusing of hacks";
			if (args.getJoinedStrings(2) != null) {
				reason = args.getJoinedStrings(2);
			}

			Mute mute = new Mute(player, who, new MuteInfractionTask(time), reason);
			mute.execute();
		}
	}

	@Command(aliases = { "warn" }, desc = "warn a player", usage = "[player] [reason]", min = 1, max = 2)
	public static void warn(final CommandContext args, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args.getString(0));

			if (target == null) {
				sender.sendMessage(RED + "No players matched query");
				return;
			}

			String reason = args.getJoinedStrings(1);
			

			Warn warn = new Warn(player, target, reason);

			warn.execute();
		}
	}

	@Command(aliases = { "lookup",
			"lu" }, desc = "lookup a player who has infractions", usage = "[player]", min = 0, max = 1)
	public static void lookup(final CommandContext args, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			Player target = Bukkit.getPlayer(args.getJoinedStrings(0));

			if (target == null) {
				player.sendMessage(RED + "No players matched query");
				return;
			}

			InfractionManager manager = InfractionManager.getInfractionManager();

			player.sendMessage(YELLOW + "- - - -" + " Record for " + RED + target.getName() + YELLOW + " - - - -");
			String first = GOLD + Characters.Raquo.getUTF();
			if (manager.hasInfractions(target)) {
				// date >> staff >> kick >> reason
				for (Infraction infraction : manager.getInfractions(target)) {
					player.sendMessage(infraction.getBy().getName() + " " + first + " " + RED + infraction.getName()
							+ " " + first + " " + YELLOW + infraction.getReason());
				}
			} else {
				player.sendMessage(RED + target.getName() + "'s" + YELLOW + " record is clean");
			}
		}
	}
}
