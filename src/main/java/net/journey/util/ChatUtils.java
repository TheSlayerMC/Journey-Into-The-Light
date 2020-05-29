package net.journey.util;

import net.journey.JITL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import ru.timeconqueror.timecore.api.util.Pair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ChatUtils {
	public static void send(EntityPlayer player, String msg) {
		send(player, new TextComponentString(msg));
	}

	public static void send(EntityPlayer player, ITextComponent msg) {
		player.sendMessage(msg);
	}

	public static void sendColored(EntityPlayer player, TextFormatting color, String msg) {
		sendColored(player, color, new TextComponentString(msg));
	}

	public static void sendColored(EntityPlayer player, TextFormatting color, ITextComponent msg) {
		player.sendMessage(color(color, msg));
	}

	public static void sendTranslated(EntityPlayer player, String translationKey, Object... args) {
		player.sendMessage(new TextComponentTranslation(translationKey, args));
	}

	public static void sendColoredTranslated(EntityPlayer player, TextFormatting color, String translationKey, Object... args) {
		player.sendMessage(color(color, new TextComponentTranslation(translationKey, args)));
	}

	public static <T extends ITextComponent> T color(TextFormatting color, T component) {
		component.getStyle().setColor(color);
		return component;
	}

	/**
	 * Sends an error message to the player and prints extra info to the log file.
	 *
	 * @param player    player to send the message
	 * @param msg       message to send
	 * @param extraInfo extra info that is present as the "name->value" pair array
	 */
	@SafeVarargs
	public static void sendInformativeError(EntityPlayer player, String msg, Pair<String, Object>... extraInfo) {
		sendColored(player, TextFormatting.RED, "Error: " + msg);
		sendColored(player, TextFormatting.RED, new TextComponentTranslation("msg.journey.error_msg", JITL.MOD_NAME));
		JITL.LOGGER.error(msg);
		JITL.LOGGER.error("Extra information: " + Arrays.toString(extraInfo));
		JITL.LOGGER.error("", new Exception(""));
	}

	public static ITextComponent bindLink(ITextComponent component, String url) {
		try {
			if (new URI(url).getScheme() == null) {
				url = "http://" + url;
			}
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}

		// Set the click event and append the link.
		ClickEvent click = new ClickEvent(ClickEvent.Action.OPEN_URL, url);
		component.getStyle().setClickEvent(click);
		component.getStyle().setUnderlined(true);
		return component;
	}
}
