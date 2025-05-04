package org.aviora.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Глобальные утилитные методы
 */
public class AvioraUtils {
	/**
	 * Получить {@code MiniMessage}
	 *
	 * @return  {@code MiniMessage}
	 * @since 1.0
	 */
	public static MiniMessage getMM() {
		return MiniMessage.miniMessage();
	}

	/**
	 * Получить стандартный баннер плагина с названием
	 *
	 * @param  name Название плагина
	 * @return  Баннер
	 * @since 1.0
	 */
	public static String getBanner(String name) {
		return "<blue>Плагин %plugin% by RitzAtemo.".replace("%plugin%", name);
	}

	/**
	 * Получить стандартное сообщение о включенном плагине
	 *
	 * @param  name Название плагина
	 * @return  Баннер
	 * @since 1.0
	 */
	public static String getEnabled(String name) {
		return "<green>Плагин %plugin% включен!".replace("%plugin%", name);
	}

	/**
	 * Получить стандартное сообщение о выключенном плагине
	 *
	 * @param  name Название плагина
	 * @return  Баннер
	 * @since 1.0
	 */
	public static String getDisabled(String name) {
		return "<red>Плагин %plugin% выключен.".replace("%plugin%", name);
	}

	/**
	 * Отправить сообщение на весь сервер (игрокам+консоли)
	 *
	 * @param  message Сообщение
	 * @since 1.0
	 */
	public static void broadcastMessage(String message) {
		broadcastMessageArgs(message);
	}

	/**
	 * Отправить сообщение на весь сервер (игрокам+консоли)
	 *
	 * @param  message Сообщение
	 * @param  args Аргумненты (четное количество, где один - шаблонизатор, а второй - значение)
	 * @since 1.0
	 */
	public static void broadcastMessageArgs(String message, String... args) {
		Bukkit.broadcast(formatText(message, args));
	}

	/**
	 * Отправить сообщение игроку
	 *
	 * @param  message Сообщение
	 * @since 1.0
	 */
	public static void sendPlayerMessage(Player player, String message) {
		sendPlayerMessageArgs(player, message);
	}

	/**
	 * Отправить сообщение игроку
	 *
	 * @param  message Сообщение
	 * @param  args Аргумненты (четное количество, где один - шаблонизатор, а второй - значение)
	 * @since 1.0
	 */
	public static void sendPlayerMessageArgs(Player player, String message, String... args) {
		player.sendMessage(formatText(message, args));
	}

	/**
	 * Отправить сообщение выполнявшему команду {@code CommandSender}
	 *
	 * @param  sender Отправитель команды
	 * @param  message Сообщение
	 * @since 1.0
	 */
	public static void sendCommandSenderMessage(CommandSender sender, String message) {
		sendCommandSenderMessageArgs(sender, message);
	}

	/**
	 * Отправить сообщение выполнявшему команду {@code CommandSender}
	 *
	 * @param  sender Отправитель команды
	 * @param  message Сообщение
     * @param  args Аргумненты (четное количество, где один - шаблонизатор, а второй - значение)
	 * @since 1.0
	 */
	public static void sendCommandSenderMessageArgs(CommandSender sender, String message, String... args) {
		sender.sendMessage(formatText(message, args));
	}

	/**
	 * Отправить лог в консоль и игрокам с доступом {@code aviora.core.log}
	 *
	 * @param  message Сообщение
	 * @since 1.0
	 */
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

	/**
	 * Форматировать текст и его шаблонизаторы через {@code MiniMessage}
	 *
	 * @param  raw Исходный текст с шаблонами и тегами цветов
	 * @param  args Аргумненты (четное количество, где один - шаблонизатор, а второй - значение)
	 * @since 1.0
	 */
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
