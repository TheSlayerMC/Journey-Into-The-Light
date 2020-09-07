package net.journey.init.common;

import net.journey.blocks.BlockMelon;
import net.journey.blocks.crop.*;
import net.journey.blocks.crop.base.BlockFruitCrop;
import net.journey.init.JourneyTabs;
import net.journey.init.Registrar;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyConsumables;
import net.journey.items.interactive.ItemFruit;
import net.journey.util.PotionEffects;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockMod;
import net.slayer.api.item.ItemModSeeds;

public class JourneyCrops {

    public static BlockMod bleedheartFruit;
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

    public static Item bleedheart;
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
        bleedheartFruit = (BlockFruitCrop) new BlockFruitCrop("bleedheartFruit", "Bleedheart Fruit", JourneyCrops.bleedheart, JourneyBlocks.sizzlerWoodLog).setCreativeTab(null);
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
        airRootMelon = new BlockMelon("airRootMelon", "Air Root", JourneyConsumables.airMelon);

        bleedheart = Registrar.regAndSetupItem("bleedheart", "Bleedheart Fruit", new ItemFruit(2, 0.1F, false, (BlockFruitCrop) JourneyCrops.bleedheartFruit, JourneyBlocks.sizzlerWoodLog).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 10), 1.0F), JourneyTabs.CROPS);
        glowshroomPowder = Registrar.regAndSetupItem("glowshroomPowder", "Glowshroom Powder", new ItemModSeeds(JourneyCrops.glowshroomCrop), JourneyTabs.CROPS);
        tomatoSeeds = Registrar.regAndSetupItem("tomatoSeeds", "Tomato Seeds", new ItemModSeeds(JourneyCrops.tomatoCrop), JourneyTabs.CROPS);
        corveggieSeeds = Registrar.regAndSetupItem("corveggieSeeds", "Corveggie", new ItemModSeeds(JourneyCrops.corveggieCrop), JourneyTabs.CROPS);
        crackenCaneSeeds = Registrar.regAndSetupItem("crackenCaneSeeds", "Kracken Cane Seeds", new ItemModSeeds(JourneyCrops.crackenCaneCrop), JourneyTabs.CROPS);
        crakeBulbSeeds = Registrar.regAndSetupItem("crakeBulbSeeds", "Crake Bulb Seeds", new ItemModSeeds(JourneyCrops.crakeBulbCrop), JourneyTabs.CROPS);
        spineberrySeeds = Registrar.regAndSetupItem("spineBerrySeeds", "Spine Berry Seeds", new ItemModSeeds(JourneyCrops.spineberryCrop), JourneyTabs.CROPS);
        glowaSeeds = Registrar.regAndSetupItem("glowaSeeds", "Glowa Seeds", new ItemModSeeds(JourneyCrops.glowaCrop), JourneyTabs.CROPS);
        zatSeeds = Registrar.regAndSetupItem("zatSeeds", "Zat Seeds", new ItemModSeeds(JourneyCrops.zatPedalsCrop), JourneyTabs.CROPS);
        floroSeeds = Registrar.regAndSetupItem("floroSeeds", "Floro Seeds", new ItemModSeeds(JourneyCrops.floroCrop), JourneyTabs.CROPS);
        airRootSeed = Registrar.regAndSetupItem("airRootSeed", "Air Root Seed", new ItemModSeeds(JourneyCrops.airRootCrop), JourneyTabs.CROPS);
    }
}
