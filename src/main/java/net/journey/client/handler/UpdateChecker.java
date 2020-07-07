package net.journey.client.handler;

import net.journey.JITL;
import net.journey.util.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class UpdateChecker {
	private static boolean shouldShow = true;

	@SubscribeEvent
	public static void onPlayerLogin(EntityJoinWorldEvent e) {
		if (shouldShow && e.getWorld().isRemote && e.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) e.getEntity();

			ChatUtils.sendColored(p, TextFormatting.GOLD, "[|--------------------------------------------------|]");
			ChatUtils.sendColored(p, TextFormatting.GOLD, "[" + JITL.MOD_NAME + "]");
			ChatUtils.sendColoredTranslated(p, TextFormatting.YELLOW, "msg.journey.current_version", JITL.MOD_VERSION);

			ForgeVersion.CheckResult result = ForgeVersion.getResult(Loader.instance().getIndexedModList().get(JITL.MOD_ID));

			if (result.status == ForgeVersion.Status.FAILED || result.status == ForgeVersion.Status.PENDING) {
				ChatUtils.sendColoredTranslated(p, TextFormatting.RED, "msg.journey.cant_get_version");
			} else if (result.status == ForgeVersion.Status.UP_TO_DATE) {
				ChatUtils.sendColoredTranslated(p, TextFormatting.GREEN, "msg.journey.up_to_date");
			} else if (result.status == ForgeVersion.Status.OUTDATED) {
				ChatUtils.sendColoredTranslated(p, TextFormatting.AQUA, "msg.journey.update_available", result.target != null ? result.target.toString() : "null");
				ITextComponent updateMsg = new TextComponentTranslation("msg.journey.update_link.base",
						ChatUtils.bindLink(new TextComponentTranslation("msg.journey.update_link.link_word"), "https://www.curseforge.com/minecraft/mc-mods/journey-into-the-light-mod"));
				ChatUtils.sendColored(p, TextFormatting.AQUA, updateMsg);
			}

			ChatUtils.sendColoredTranslated(p, TextFormatting.YELLOW, "msg.journey.twitter_link", "@JourneyMod");
			ChatUtils.sendColored(p, TextFormatting.GOLD, "[|--------------------------------------------------|]");

			shouldShow = false;
		}
	}
}