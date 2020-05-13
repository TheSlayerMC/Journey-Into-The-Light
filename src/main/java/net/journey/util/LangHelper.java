package net.journey.util;

import net.journey.JITL;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.server.command.TextComponentHelper;

public class LangHelper {

	public String[] updateAvaliable = new String[]{};

	public static String getFormattedText(String format) {
		return i18n(format);
	}

	public static ITextComponent getClientSideTranslation(ICommandSender sender, String str, final Object... args) {
		return TextComponentHelper.createComponentTranslation(sender, str, args);
	}

	public static String i18n(String text, Object... args) {
		TextComponentTranslation result = new TextComponentTranslation(text, args);
		result.getStyle().setColor(TextFormatting.GRAY);
		return result.getFormattedText();
	}

	public static String getEfficiency() {
		return getFormattedText("journey.efficiency");
	}

	public static String getUsesRemaining() {
		return getFormattedText("journey.usesRemaining");
	}

	public static String getInfiniteUses() {
		return getFormattedText("journey.infinite");
	}

	public static String useEssence(int amount) {
		return getFormattedText("journey.uses") + " " + amount + " " + getFormattedText("journey.essence");
	}

	public static String rangedDamage(int damage) {
		return damage + " " + getFormattedText("journey.rangedDamage");
	}

	public static String unbreakable() {
		return getFormattedText("journey.unbreakable");
	}

	public static String setWitherSword(int time) {
		return getFormattedText("journey.hit") + " " + getFormattedText("journey.wither") + " " + time + " " + getFormattedText("journey.seconds");
	}

	public static String setBossSpawner(String boss) {
		return getFormattedText("journey.bossSpawn") + ": " + boss;
	}

	public static String setPetSpawner(String pet) {
		return getFormattedText("journey.petSpawn") + ": " + pet;
	}

	/**
	 * Makes key from provided name.
	 */
	public static String nameToKey(String name) {
		return name.trim().replace(" ", "_").toLowerCase();
	}

	public static String withModPrefix(String str) {
		return JITL.MOD_ID + "." + str;
	}
}