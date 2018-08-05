package net.journey.util;

import net.minecraft.client.resources.I18n;

public class LangHelper {
	
	public String[] updateAvaliable = new String[]{	};
	
	public static String getFormattedText(String format) {
		return I18n.format(format, new Object[0]);
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
}