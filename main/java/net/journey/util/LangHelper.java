package net.journey.util;

import net.minecraft.client.resources.I18n;

public class LangHelper {
	
	public String[] updateAvaliable = new String[]{	};
	
	public static String getFormattedText(String format) {
		return I18n.format(format, new Object[0]);
	}
	
	public static String getEfficiency() {
		return getFormattedText("essence.efficiency");
	}
	
	public static String getUsesRemaining() {
		return getFormattedText("essence.usesRemaining");
	}
	
	public static String getInfiniteUses() {
		return getFormattedText("essence.infinite");
	}
	
	public static String useEssence(int amount) {
		return getFormattedText("essence.uses") + " " + amount + " " + getFormattedText("essence.essence");
	}
	
	public static String useDarkEnergy(int amount) {
		return getFormattedText("essence.uses") + " " + amount + " " + getFormattedText("essence.dark");
	}
	
	public static String rangedDamage(int damage) {
		return damage + " " + getFormattedText("essence.rangedDamage");
	}
	
	public static String unbreakable() {
		return getFormattedText("essence.unbreakable");
	}
	
	public static String setWitherSword(int time) {
		return getFormattedText("essence.hit") + " " + getFormattedText("essence.wither") + " " + time + " " + getFormattedText("essence.seconds");
	}
	
	public static String setBossSpawner(String boss) {
		return getFormattedText("essence.bossSpawn") + ": " + boss;
	}
	
	public static String setPetSpawner(String pet) {
		return getFormattedText("essence.petSpawn") + ": " + pet;
	}
}