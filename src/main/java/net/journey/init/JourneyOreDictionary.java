package net.journey.init;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraftforge.oredict.OreDictionary;

public class JourneyOreDictionary {

    public static void init() {
        initOreBlocks();
        initOreItems();
        initLogBlocks();
        initPlankBlocks();
    }

    public static void initOreBlocks() {
        OreDictionary.registerOre("oreSapphire", JourneyBlocks.sapphireOre);
        OreDictionary.registerOre("oreAshual", JourneyBlocks.ashualOre);
        OreDictionary.registerOre("oreBlazium", JourneyBlocks.blaziumOre);
        OreDictionary.registerOre("oreCelestium", JourneyBlocks.celestiumOre);
        OreDictionary.registerOre("oreDes", JourneyBlocks.desOre);
        OreDictionary.registerOre("oreEnderillium", JourneyBlocks.enderilliumOre);
        OreDictionary.registerOre("oreOrbadite", JourneyBlocks.orbaditeOre);
        OreDictionary.registerOre("oreFirestone", JourneyBlocks.firestoneOre);
        OreDictionary.registerOre("oreFlairium", JourneyBlocks.flairiumOre);
        OreDictionary.registerOre("oreGorbite", JourneyBlocks.gorbiteOre);
        OreDictionary.registerOre("oreHellstone", JourneyBlocks.hellstoneOre);
        OreDictionary.registerOre("oreIridium", JourneyBlocks.iridiumOre);
        OreDictionary.registerOre("oreKorite", JourneyBlocks.koriteOre);
        OreDictionary.registerOre("oreLunite", JourneyBlocks.luniteOre);
        OreDictionary.registerOre("oreLunium", JourneyBlocks.luniumOre);
        OreDictionary.registerOre("oreMekyum", JourneyBlocks.mekyumOre);
        OreDictionary.registerOre("oreShadium", JourneyBlocks.shadiumOre);
        OreDictionary.registerOre("oreStoron", JourneyBlocks.storonOre);
    }

    public static void initOreItems() {
        OreDictionary.registerOre("gemSapphire", JourneyItems.sapphire);
        OreDictionary.registerOre("gemBleedstone", JourneyItems.bleedstone);
        OreDictionary.registerOre("gemSmithstone", JourneyItems.smithstone);
        OreDictionary.registerOre("ingotHellstone", JourneyItems.hellstoneIngot);
        OreDictionary.registerOre("ingotCelestium", JourneyItems.celestiumIngot);
        OreDictionary.registerOre("ingotDes", JourneyItems.desIngot);
        OreDictionary.registerOre("ingotFlairium", JourneyItems.flairiumIngot);
        OreDictionary.registerOre("ingotHellcrust", JourneyItems.hellcrustIngot);
        OreDictionary.registerOre("ingotKorite", JourneyItems.koriteIngot);
        OreDictionary.registerOre("ingotLunium", JourneyItems.luniumIngot);
        OreDictionary.registerOre("ingotMekyum", JourneyItems.mekyumIngot);
        OreDictionary.registerOre("ingotOrbadite", JourneyItems.orbaditeIngot);
        OreDictionary.registerOre("ingotShadium", JourneyItems.shadiumIngot);
        OreDictionary.registerOre("ingotStoron", JourneyItems.storonIngot);
    }

    public static void initLogBlocks() {
        OreDictionary.registerOre("logWood", JourneyBlocks.eucaGoldLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.boilingLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.earthenNetherLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.cloudiaLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.corbaLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.depthsLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.frozenLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.iceLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.sizzlerWoodLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.terranianLog);
        OreDictionary.registerOre("logWood", JourneyBlocks.witherwoodLog);
    }

    public static void initPlankBlocks() {
        OreDictionary.registerOre("plankWood", JourneyBlocks.goldEucaPlank);
        OreDictionary.registerOre("plankWood", JourneyBlocks.corbaPlank);
        OreDictionary.registerOre("plankWood", JourneyBlocks.depthsPlank);
        OreDictionary.registerOre("plankWood", JourneyBlocks.cloudiaPlanks);
        OreDictionary.registerOre("plankWood", JourneyBlocks.earthenNetherPlanks);
        OreDictionary.registerOre("plankWood", JourneyBlocks.frozenPlanks);
    }
}
