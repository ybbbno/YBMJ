package me.deadybbb.ybmj;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.util.regex.Pattern;

public class LegacyTextHandler {
    private static final Pattern LEGACY_PATTERN = Pattern.compile("&[0-9a-fk-or]");
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final PlainTextComponentSerializer PLAIN_TEXT_SERIALIZER = PlainTextComponentSerializer.plainText();
    private static final LegacyComponentSerializer LEGACY_TEXT_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    public static Component parseText(String text) {
        Component msg;
        if (LEGACY_PATTERN.matcher(text).find()) {
            msg = LEGACY_TEXT_SERIALIZER.deserialize(text);
        } else {
            msg = MINI_MESSAGE.deserialize(text);
        }
        return msg;
    }

    public static void sendFormattedMessage(CommandSender sender, String text) {
        sender.sendMessage(parseText(text));
    }

    public static void sendFormattedMessage(CommandSender sender, Component text) {
        sender.sendMessage(text);
    }

    public static String stripFormatting(String text) {
        String plainText = LEGACY_PATTERN.matcher(text).replaceAll("");
        return PLAIN_TEXT_SERIALIZER.serialize(parseText(plainText));
    }

    public static String formatLocation(Location loc) {
        return String.format("(%.2f, %.2f, %.2f)", loc.getX(), loc.getY(), loc.getZ());
    }
}
