package net.journey.util;

import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.slayer.api.SlayerAPI;

public enum EnumArmor {

	BLOODCRUST(SlayerAPI.addArmorMaterial("bloodcrust", 3000, new int[] {3, 4, 3, 2}, 10), "bloodcrust", false, 50, JourneyItems.hellstoneIngot, "Bloodcrust"),
	HELLSTONE(SlayerAPI.addArmorMaterial("hellstone", 3000, new int[] {3, 4, 3, 2}, 10), "hellstone", false, 50, JourneyItems.hellstoneIngot, "Hellstone"),
	FLAIRIUM(SlayerAPI.addArmorMaterial("flairium", 3000, new int[] {3, 4, 3, 2}, 10), "flairium", false, 70, JourneyItems.flairiumIngot, "Flairium"),
	CELESTIUM(SlayerAPI.addArmorMaterial("celestium", 3000, new int[] {3, 4, 3, 2}, 10), "celestium", false, 65, JourneyItems.celestiumIngot, "Celestium"),
	LUNIUM(SlayerAPI.addArmorMaterial("lunium", 3000, new int[] {3, 4, 3, 2}, 10), "lunium", false, 60, JourneyItems.luniumIngot, "Lunium"),
	SHADIUM(SlayerAPI.addArmorMaterial("shadium", 3000, new int[] {3, 4, 3, 2}, 10), "shadium", false, 60, JourneyItems.shadiumIngot, "Shadium"),
	SAPPHIRE(SlayerAPI.addArmorMaterial("sapphire", 3000, new int[] {3, 4, 3, 2}, 10), "sapphire", false, 60, JourneyItems.sapphire, "Sapphire"),
	ORBADITE(SlayerAPI.addArmorMaterial("orbadite", 3000, new int[] {3, 4, 3, 2}, 10), "orbadite", false, 86, JourneyItems.orbaditeIngot, "Orbadite"),
	GORBITE(SlayerAPI.addArmorMaterial("gorbite", 3000, new int[] {3, 4, 3, 2,}, 10), "gorbite", false, 86, JourneyItems.gorbiteGem, "Gorbite"),
	FLAME(SlayerAPI.addArmorMaterial("flame", 3000, new int[] {3, 4, 3, 2}, 10), "flame", false, 76, (Item)null, "Flame"),
	TWILIGHT(SlayerAPI.addArmorMaterial("twilight", 3000, new int[] {3, 4, 3, 2}, 10), "twilight", false, 66, (Item)null, "Twilight"),
	SNAKESKIN(SlayerAPI.addArmorMaterial("snakeskin", 3000, new int[] {3, 4, 3, 2,}, 10), "snakeskin", false, 66, (Item)null, "Snakeskin"),
	CHAR_SKULL(SlayerAPI.addArmorMaterial("charSkull", 3000, new int[] {3, 4, 3, 2}, 10), "charSkull", false, 66, (Item)null, "Char Skull"),
	LEAPERS(SlayerAPI.addArmorMaterial("leapers", 3000, new int[] {3, 4, 3, 2}, 10), "leapers", false, 86, (Item)null, "Leapers"),
	TREEHUGGERS(SlayerAPI.addArmorMaterial("treehuggers", 3000, new int[] {3, 4, 3, 2}, 10), "treehuggers", false, 86, (Item)null, "Tree Huggers"),
	BRONZED(SlayerAPI.addArmorMaterial("bronzed", 3000, new int[] {3, 4, 3, 2}, 10), "bronzed", false, 76, (Item)null, "Bronzed"),
	GOLDITE(SlayerAPI.addArmorMaterial("goldite", 3000, new int[] {3, 4, 3, 2}, 10), "goldite", false, 76, (Item)null, "Goldite"),
	CORBARK(SlayerAPI.addArmorMaterial("corkbark", 3000, new int[] {3, 4, 3, 2}, 10), "corbark", false, 86, (Item)null, "Corbark"),
	DARKLY(SlayerAPI.addArmorMaterial("darkly", 3000, new int[] {3, 4, 3, 2}, 10), "darkly", false, 86, (Item)null, "Darkly"),
	DEPTHS(SlayerAPI.addArmorMaterial("depths", 3000, new int[] {3, 4, 3, 2}, 10), "depths", false, 86, (Item)null, "Depths"),
	ENLIGHTENER(SlayerAPI.addArmorMaterial("enlightener", 3000, new int[] {3, 4, 3, 2}, 10), "enlightener", false, 96, (Item)null, "Enlightener"),
	FIREBOUND(SlayerAPI.addArmorMaterial("firebound", 3000, new int[] {3, 4, 3, 2}, 10), "firebound", false, 86, (Item)null, "Firebound"),
	FROSTBITTEN(SlayerAPI.addArmorMaterial("frostbitten", 3000, new int[] {3, 4, 3, 2}, 10), "frostbitten", false, 66, (Item)null, "Frostbitten"),
	HOLLOW(SlayerAPI.addArmorMaterial("hollow", 3000, new int[] {3, 4, 3, 2}, 10), "hollow", false, 86, (Item)null, "Hollow"),
	LIGHTSTONE(SlayerAPI.addArmorMaterial("lightstone", 3000, new int[] {3, 4, 3, 2}, 10), "lightstone", false, 86, (Item)null, "Lightstone"),
	LIVEGREEN(SlayerAPI.addArmorMaterial("livegreen", 3000, new int[] {3, 4, 3, 2}, 10), "livegreen", false, 86, (Item)null, "Livegreen"),
	STARLIGHT(SlayerAPI.addArmorMaterial("starlight", 3000, new int[] {3, 4, 3, 2}, 10), "starlight", false, 76, (Item)null, "Starlight"),
	BLEEDROCK(SlayerAPI.addArmorMaterial("bleedrock", 3000, new int[] {3, 4, 3, 2}, 10), "bleedrock", false, 76, (Item)null, "Bleedrock"),
	BLAZEHORN(SlayerAPI.addArmorMaterial("blazehorn", 3000, new int[] {3, 4, 3, 2}, 10), "blazehorn", false, 86, (Item)null, "Blazehorn"),
	
	CRYSTAL_FLAKE(SlayerAPI.addArmorMaterial("crystalFlake", 3000, new int[] {3, 4, 3, 2}, 10), "crystalFlake", false, 86, (Item)null, "Crystal Flake");
	
	private ArmorMaterial armorMaterial;
	private String type, finalName;
	private boolean undamageable;
	private int damageReduction;
	private Item repairItem;

	private EnumArmor(ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction, Item repair, String finalName) {
		this.armorMaterial = armorMaterial;
		this.type = type;
		this.finalName = finalName;
		this.undamageable = undamageable;
		this.damageReduction = damageReduction;
		this.repairItem = repair;
	}

	private EnumArmor(ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction, Block repair, String finalName) {
		this.armorMaterial = armorMaterial;
		this.type = type;
		this.finalName = finalName;
		this.undamageable = undamageable;
		this.damageReduction = damageReduction;
		this.repairItem = SlayerAPI.toItem(repair);
	}

	public Item getRepairItem(){
		return repairItem;
	}

	public ArmorMaterial getArmorMaterial() {
		return armorMaterial;
	}

	public String getType() {
		return type;
	}
	
	public String getFinalName() {
		return finalName;
	}

	public boolean isUndamageable() {
		return undamageable;
	}

	public int getDamageReduction() {
		return damageReduction;
	}
}