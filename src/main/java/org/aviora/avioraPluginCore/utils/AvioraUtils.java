package org.aviora.avioraPluginCore.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AvioraUtils {

	public static MiniMessage getMM() {
		return MiniMessage.miniMessage();
	}

	public static String getBanner(String name) {
		return "<blue>Plugin %plugin% by RitzAtemo.".replace("%plugin%", name);
	}

	public static String getEnabled(String name) {
		return "<green>Plugin %plugin% enabled!".replace("%plugin%", name);
	}

	public static String getDisabled(String name) {
		return "<red>Plugin %plugin% disabled.".replace("%plugin%", name);
	}

	public static void broadcastMessage(String message) {
		broadcastMessageArgs(message);
	}

	public static void broadcastMessageArgs(String message, String... args) {
		Bukkit.broadcast(formatText(message, args));
	}

	public static void sendPlayerMessage(Player player, String message) {
		sendPlayerMessageArgs(player, message);
	}

	public static void sendPlayerMessageArgs(Player player, String message, String... args) {
		player.sendMessage(formatText(message, args));
	}

	public static void sendCommandSenderMessage(CommandSender sender, String message) {
		sendCommandSenderMessageArgs(sender, message);
	}

	public static void sendCommandSenderMessageArgs(CommandSender sender, String message, String... args) {
		sender.sendMessage(formatText(message, args));
	}

	public static void log(String message) {
		Component formatted = formatText(message);

		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(formatted);

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("aviora.core.log")) {
				player.sendMessage(formatted);
			}
		}
	}

	public static Component formatText(String raw, String... args) {
		if (raw.isBlank()) {
			return Component.text("");
		}

		if (args.length % 2 != 0) {
			return Component.text(raw);
		}

		for (int i = 0; i < args.length; i += 2) {
			raw = raw.replace(args[i], args[i + 1]);
		}

		try {
			return getMM().deserialize(raw);
		} catch (Exception e) {
			return Component.text(raw);
		}
	}

}
