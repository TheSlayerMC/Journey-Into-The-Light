package net.journey;

import java.util.ArrayList;

import net.journey.blocks.BlockMelon;
import net.journey.blocks.crop.BlockAirRootCrop;
import net.journey.blocks.crop.BlockCorveggieCrop;
import net.journey.blocks.crop.BlockCrackenCaneCrop;
import net.journey.blocks.crop.BlockCrakeBulbCrop;
import net.journey.blocks.crop.BlockFloroCrop;
import net.journey.blocks.crop.BlockGlowaCrop;
import net.journey.blocks.crop.BlockGlowshroomCrop;
import net.journey.blocks.crop.BlockSpineberryCrop;
import net.journey.blocks.crop.BlockTomatoCrop;
import net.journey.blocks.crop.BlockZatPedalsCrop;
import net.journey.blocks.crop.base.BlockFruitCrop;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.slayer.api.item.ItemModSeeds;

public class JourneyCrops {

	public static Block tomatoCrop;
	public static Block corveggieCrop;
	public static Block crackenCaneCrop;
	public static Block crakeBulbCrop;
	public static Block spineberryCrop;
	public static Block glowaCrop;
	public static Block zatPedalsCrop;
	public static Block glowshroomCrop;
	public static Block floroCrop;
	public static Block airRootCrop;
	public static Block airRootMelon;
	public static Block bleedheartFruit;

	public static Item glowshroomPowder;
	public static Item tomatoSeeds;
	public static Item corveggieSeeds;
	public static Item crackenCaneSeeds;
	public static Item crakeBulbSeeds;
	public static Item spineberrySeeds;
	public static Item glowaSeeds;
	public static Item zatSeeds;
	public static Item floroSeeds;
	public static Item airRootSeed;

	public static void init() {
		tomatoCrop = new BlockTomatoCrop("tomatoCrop");
		corveggieCrop = new BlockCorveggieCrop("corveggiesCrop");
		crackenCaneCrop = new BlockCrackenCaneCrop("crackenCanesCrop");
		crakeBulbCrop = new BlockCrakeBulbCrop("crakeBulbCrop");
		spineberryCrop = new BlockSpineberryCrop("spineberryCrop");
		glowaCrop = new BlockGlowaCrop("glowaCrop");
		zatPedalsCrop = new BlockZatPedalsCrop("zatPedalsCrop");
		glowshroomCrop = new BlockGlowshroomCrop("glowshroomCrop");
		floroCrop = new BlockFloroCrop("floroCrop");
		airRootCrop = new BlockAirRootCrop("airRootCrop");
		airRootMelon = new BlockMelon("airRootMelon", "Air Root", JourneyItems.airMelon);
		bleedheartFruit = new BlockFruitCrop("bleedheartFruitCrop");

		glowshroomPowder = new ItemModSeeds("glowshroomPowder", "Glowshroom Powder", JourneyCrops.glowshroomCrop);
		tomatoSeeds = new ItemModSeeds("tomatoSeeds", "Tomato Seeds", JourneyCrops.tomatoCrop);
		corveggieSeeds = new ItemModSeeds("corveggieSeeds", "Corveggie", JourneyCrops.corveggieCrop);
		crackenCaneSeeds = new ItemModSeeds("crackenCaneSeeds", "Kracken Cane Seeds", JourneyCrops.crackenCaneCrop);
		crakeBulbSeeds = new ItemModSeeds("crakeBulbSeeds", "Crake Bulb Seeds", JourneyCrops.crakeBulbCrop);
		spineberrySeeds = new ItemModSeeds("spineBerrySeeds", "Spine Berry Seeds", JourneyCrops.spineberryCrop);
		glowaSeeds = new ItemModSeeds("glowaSeeds", "Glowa Seeds", JourneyCrops.glowaCrop);
		zatSeeds = new ItemModSeeds("zatSeeds", "Zat Seeds", JourneyCrops.zatPedalsCrop);
		floroSeeds = new ItemModSeeds("floroSeeds", "Floro Seeds", JourneyCrops.floroCrop);
		airRootSeed = new ItemModSeeds("airRootSeed", "Air Root Seed", JourneyCrops.airRootCrop);
	}
}
