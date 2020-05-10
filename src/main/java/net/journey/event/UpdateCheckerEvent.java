package net.journey.event;

import net.journey.JITL;
import net.journey.util.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

@Mod.EventBusSubscriber(Side.CLIENT)
public class UpdateCheckerEvent {
	private static boolean shouldShow = true;

	@SubscribeEvent
	public static void onPlayerLogin(EntityJoinWorldEvent e) {
		if (shouldShow && e.getWorld().isRemote && e.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) e.getEntity();

			String downloadedVersion = null;
			try {
				URL url = new URL("https://raw.githubusercontent.com/TheSlayerMC/Journey-Into-The-Light/master/VER.txt");
				InputStream stream = url.openStream();

				downloadedVersion = IOUtils.toString(stream, StandardCharsets.UTF_8);

				stream.close();

			} catch (UnknownHostException ex) {
				JITL.LOGGER.warn("Can't get latest available version. Check your internet connection!");
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			ChatUtils.sendColored(p, TextFormatting.GOLD, "[|--------------------------------------------------|]");
			ChatUtils.sendColored(p, TextFormatting.GOLD, "[" + JITL.MOD_NAME + "]");
			ChatUtils.sendColoredTranslated(p, TextFormatting.YELLOW, "Current version: " + JITL.MOD_VERSION + "");
			if (downloadedVersion == null) {
				ChatUtils.sendColored(p, TextFormatting.RED, "Unable to get latest version. Check your internet connection.");
			} else if (JITL.MOD_VERSION.equals(downloadedVersion)) {
				ChatUtils.sendColored(p, TextFormatting.GREEN, "Your version is up to date!");
			} else {
				ChatUtils.sendColored(p, TextFormatting.AQUA, "Update is available! Latest version: " + downloadedVersion);
				ITextComponent updateMsg = new TextComponentString("You can get it ");
				updateMsg.appendSibling(ChatUtils.bindLink(new TextComponentString("here"), "https://www.curseforge.com/minecraft/mc-mods/journey-into-the-light-mod"));
				updateMsg.appendSibling(new TextComponentString("!"));
				ChatUtils.sendColored(p, TextFormatting.AQUA, updateMsg);
			}

			ChatUtils.sendColored(p, TextFormatting.YELLOW, "Follow @JourneyMod on twitter for update teasers.");
			ChatUtils.sendColored(p, TextFormatting.GOLD, "[|--------------------------------------------------|]");

			shouldShow = false;
		}
	}
}