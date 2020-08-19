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
		initStoneBlocks();
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

	    OreDictionary.registerOre("blockSapphire", JourneyBlocks.sapphireBlock);
	    OreDictionary.registerOre("blockAshual", JourneyBlocks.ashualBlock);
	    OreDictionary.registerOre("blockBlazium", JourneyBlocks.blaziumBlock);
	    OreDictionary.registerOre("blockCelestium", JourneyBlocks.celestiumBlock);
	    OreDictionary.registerOre("blockDes", JourneyBlocks.desBlock);
	    OreDictionary.registerOre("blockEnderillium", JourneyBlocks.enderilliumBlock);
	    OreDictionary.registerOre("blockOrbadite", JourneyBlocks.orbaditeBlock);
	    OreDictionary.registerOre("blockFlairium", JourneyBlocks.flairiumBlock);
	    OreDictionary.registerOre("blockGorbite", JourneyBlocks.gorbiteBlock);
	    OreDictionary.registerOre("blockHellstone", JourneyBlocks.hellstoneBlock);
	    OreDictionary.registerOre("blockIridium", JourneyBlocks.iridiumBlock);
	    OreDictionary.registerOre("blockKorite", JourneyBlocks.koriteBlock);
	    OreDictionary.registerOre("blockLunite", JourneyBlocks.luniteBlock);
	    OreDictionary.registerOre("blockLunium", JourneyBlocks.luniumBlock);
	    OreDictionary.registerOre("blockMekyum", JourneyBlocks.mekyumBlock);
	    OreDictionary.registerOre("blockShadium", JourneyBlocks.shadiumBlock);
	    OreDictionary.registerOre("blockStoron", JourneyBlocks.storonBlock);
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

	public static void initStoneBlocks() {
		OreDictionary.registerOre("stone", JourneyBlocks.workshopStone);
		OreDictionary.registerOre("stone", JourneyBlocks.corbaStone);
		OreDictionary.registerOre("stone", JourneyBlocks.depthsStone);
		OreDictionary.registerOre("stone", JourneyBlocks.cloudiaRock);
		OreDictionary.registerOre("stone", JourneyBlocks.eucaStone);
		OreDictionary.registerOre("stone", JourneyBlocks.ashBlock);
		OreDictionary.registerOre("stone", JourneyBlocks.scorchedRubble);
		OreDictionary.registerOre("stone", JourneyBlocks.frozenStone);
		OreDictionary.registerOre("stone", JourneyBlocks.terranianStone);
	}
}
