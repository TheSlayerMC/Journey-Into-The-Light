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
	public static ArrayList<String> blockName = new ArrayList<String>();
	
	public static final Block tomatoCrop = new BlockTomatoCrop("tomatoCrop");
	public static final Block corveggieCrop = new BlockCorveggieCrop("corveggiesCrop");
	public static final Block crackenCaneCrop = new BlockCrackenCaneCrop("crackenCanesCrop");
	public static final Block crakeBulbCrop = new BlockCrakeBulbCrop("crakeBulbCrop");
	public static final Block spineberryCrop = new BlockSpineberryCrop("spineberryCrop");
	public static final Block glowaCrop = new BlockGlowaCrop("glowaCrop");
	public static final Block zatPedalsCrop = new BlockZatPedalsCrop("zatPedalsCrop");
	public static final Block glowshroomCrop = new BlockGlowshroomCrop("glowshroomCrop");
	public static final Block floroCrop = new BlockFloroCrop("floroCrop");
	public static final Block airRootCrop = new BlockAirRootCrop("airRootCrop");
	public static final Block airRootMelon = new BlockMelon("airRootMelon", "Air Root", JourneyItems.airMelon);
	public static final Block bleedheartFruit = new BlockFruitCrop("bleedheartFruitCrop");

	public static final Item glowshroomPowder = new ItemModSeeds("glowshroomPowder", "Glowshroom Powder", JourneyCrops.glowshroomCrop);
	public static final Item tomatoSeeds = new ItemModSeeds("tomatoSeeds", "Tomato Seeds", JourneyCrops.tomatoCrop);
	public static final Item corveggieSeeds = new ItemModSeeds("corveggieSeeds", "Corveggie", JourneyCrops.corveggieCrop);
	public static final Item crackenCaneSeeds = new ItemModSeeds("crackenCaneSeeds", "Kracken Cane Seeds", JourneyCrops.crackenCaneCrop);
	public static final Item crakeBulbSeeds = new ItemModSeeds("crakeBulbSeeds", "Crake Bulb Seeds", JourneyCrops.crakeBulbCrop);
	public static final Item spineberrySeeds = new ItemModSeeds("spineBerrySeeds", "Spine Berry Seeds", JourneyCrops.spineberryCrop);
	public static final Item glowaSeeds = new ItemModSeeds("glowaSeeds", "Glowa Seeds", JourneyCrops.glowaCrop);
	public static final Item zatSeeds = new ItemModSeeds("zatSeeds", "Zat Seeds", JourneyCrops.zatPedalsCrop);
	public static final Item floroSeeds = new ItemModSeeds("floroSeeds", "Floro Seeds", JourneyCrops.floroCrop);
	public static final Item airRootSeed = new ItemModSeeds("airRootSeed", "Air Root Seed", JourneyCrops.airRootCrop);
}
