package net.journey.init;

import com.google.common.base.Preconditions;
import net.journey.JITL;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.common.JourneyCrops;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.gen.RecipeGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JourneyRecipes {
	private static final RecipeGenerator GENERATOR;

	static {
		RecipeGenerator gen = null;
		if (JITL.IN_JOURNEY_DEV) {
			try {
				gen = new RecipeGenerator();
			} catch (IOException e) {
				JITL.LOGGER.error("Can't instantiate recipe generator. Recipe generating will be skipped.");
				e.printStackTrace();
			}
		}

		GENERATOR = gen;
	}

	public static void init() {
		generateRecipes();

		initFurnaceRecipes();
	}

	// will be applied next launch after being generated
	public static void generateRecipes() {
		addShapedRecipe(JourneyBlocks.greenGemBlock, "iii", "iii", "iii", 'i', JourneyItems.greenGem);
		addShapedRecipe(JourneyBlocks.purpleGemBlock, "iii", "iii", "iii", 'i', JourneyItems.purpleGem);
		addShapedRecipe(JourneyBlocks.blueGemBlock, "iii", "iii", "iii", 'i', JourneyItems.blueGem);
		addShapedRecipe(JourneyBlocks.yellowGemBlock, "iii", "iii", "iii", 'i', JourneyItems.yellowGem);

		addShapedRecipe(JourneyItems.nauseaRing, " i ", "i i", " i ", 'i', Blocks.OBSIDIAN);

		addShapedRecipe(JourneyItems.aquaticAmulet, " i ", "ixi", 'i', JourneyItems.AQUASTONE, 'x', JourneyItems.emptyAmulet);

		addShapedRecipe(JourneyBlocks.magicExplosive, "ixi", "xix", "ixi", 'i', JourneyItems.MAGIC_DUST, 'x', Blocks.SAND);

		addShapedRecipe(JourneyBlocks.INCUBATOR, "iii", "jkj", "ckc", 'i', Blocks.OBSIDIAN, 'j', Items.IRON_INGOT, 'k', JourneyItems.luniumIngot, 'c', Blocks.COBBLESTONE);

		addShapedRecipe(new ItemStack(JourneyItems.greenGem, 9), "i", 'i', JourneyBlocks.greenGemBlock);
		addShapedRecipe(new ItemStack(JourneyItems.purpleGem, 9), "i", 'i', JourneyBlocks.purpleGemBlock);
		addShapedRecipe(new ItemStack(JourneyItems.blueGem, 9), "i", 'i', JourneyBlocks.blueGemBlock);
		addShapedRecipe(new ItemStack(JourneyItems.yellowGem, 9), "i", 'i', JourneyBlocks.yellowGemBlock);

		addShapedRecipe(JourneyBlocks.igniter, "iii", "jkj", "cfc", 'i', Blocks.NETHERRACK, 'j', Items.IRON_INGOT, 'k', JourneyBlocks.lavaRock, 'c', Blocks.COBBLESTONE, 'f', Items.FLINT_AND_STEEL);
		addShapedRecipe(new ItemStack(JourneyBlocks.eucaPortalFrame, 10), "iii", "iii", "iii", 'i', JourneyItems.eucaPortalGem);
		addShapedRecipe(new ItemStack(JourneyBlocks.depthsPortalFrame, 12), "iii", "iii", "iii", 'i', JourneyItems.depthsPortalGem);
		addShapedRecipe(new ItemStack(JourneyBlocks.cloudiaPortalFrame, 10), "iii", "iii", "iii", 'i', JourneyItems.cloudiaPortalGem);
		addShapedRecipe(new ItemStack(JourneyBlocks.corbaPortalFrame, 12), "iii", "iii", 'i', JourneyItems.corbaPortalGem);
		addShapedRecipe(new ItemStack(JourneyBlocks.terraniaPortalFrame, 10), "iii", "iii", "iii", 'i', JourneyItems.terraniaPortalGem);
		addShapedRecipe(new ItemStack(JourneyBlocks.frozenPortalFrame, 10), "iii", "idi", "iii", 'i', Items.SNOWBALL, 'd', Items.DIAMOND);
		addShapedRecipe(JourneyBlocks.summoningTable, "dsd", "iii", 'i', JourneyBlocks.bloodRock, 'd', JourneyItems.hellstoneIngot, 's', JourneyItems.bleedstone);
		addShapedRecipe(new ItemStack(JourneyBlocks.boilingBars, 4), "ddd", "ddd", 'd', Items.BLAZE_ROD);
		addShapedRecipe(new ItemStack(JourneyItems.obsidianRod, 2), "d", "d", 'd', Blocks.OBSIDIAN);
		addShapedRecipe(new ItemStack(JourneyBlocks.stoneCraftingTable, 1), "dd", "dd", 'd', Blocks.COBBLESTONE);
		addShapedRecipe(JourneyBlocks.trophy, "d d", "ddd", " d ", 'd', Items.GOLD_INGOT);
		addShapedRecipe(JourneyBlocks.grindstone, " o ", "bib", "bbb", 'b', Blocks.BRICK_BLOCK, 'o', Blocks.OBSIDIAN, 'i', Items.IRON_INGOT);
		addShapedRecipe(Blocks.WEB, "sss", "sss", "sss", 's', Items.STRING);
//		addShapedRecipe(JourneyBlocks.netherFurnace, "ddd", "d d", "ddd", 'd', Blocks.NETHERRACK);
		addShapedRecipe(JourneyItems.flameCoin, "iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND);
		addShapedRecipe(JourneyItems.hellcrustIngot, "iii", "idi", "iii", 'i', JourneyItems.ash, 'd', JourneyItems.hellstoneIngot);
		addShapedRecipe(JourneyItems.reinforcedStoneIngot, "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', Blocks.STONE);
		addShapedRecipe(JourneyItems.reinforcedCrystalIngot, "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', JourneyItems.caveCrystal);
		addShapedRecipe(JourneyItems.crystalBall, "idi", "iii", 'i', JourneyItems.MAGIC_DUST, 'd', Items.ENDER_PEARL);
		addShapedRecipe(new ItemStack(JourneyBlocks.senterianPortalFrame, 4), "iii", "iii", 'i', JourneyBlocks.ANCIENT_STONE);
		addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16), "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall);
		addShapedRecipe(JourneyWeapons.MAGIC_BOMB, "ddd", "did", "ddd", 'd', JourneyItems.MAGIC_DUST, 'i', JourneyItems.crystalBall);
		addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 4), "ddd", "ddd", "ddd", 'd', JourneyItems.caveDust);
		addShapedRecipe(new ItemStack(JourneyItems.stoneStick, 16), "d", "d", "d", 'd', Blocks.STONE);
		addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16), "ddd", "ddd", "ddd", 'd', Blocks.STONE);

		addShapedRecipe(new ItemStack(JourneyBlocks.bloodBricks, 4), "dd", "dd", 'd', JourneyBlocks.bloodRock);
		addShapedRecipe(new ItemStack(JourneyBlocks.carvedBloodRock, 4), "dd", "dd", 'd', JourneyBlocks.bloodBricks);
		addShapedRecipe(new ItemStack(JourneyBlocks.bloodLamp, 4), "ddd", "did", "ddd", 'i', Blocks.GLOWSTONE, 'd', JourneyBlocks.bloodRock);
		addShapedRecipe(JourneyBlocks.obelisk, "ddd", "did", "ddd", 'i', JourneyBlocks.bloodRock, 'd', JourneyItems.boilPowder);
		addShapedRecipe(JourneyBlocks.bloodCatalyst, "ddd", "did", "ddd", 'i', JourneyBlocks.bloodRock, 'd', JourneyItems.blood);
		addShapedRecipe(new ItemStack(JourneyBlocks.bloodRune, 4), "ddd", "did", "ddd", 'd', JourneyBlocks.bloodRock, 'i', JourneyItems.BALMY_TEARDROP);
		addShapedRecipe(new ItemStack(JourneyBlocks.bloodPillar, 4), "dd", "dd", 'd', JourneyBlocks.carvedBloodRock);

		addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16), "ddd", "ddd", "ddd", 'd', Blocks.COBBLESTONE);
		addShapedRecipe(JourneyItems.withicSoul, "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.withicDust);
		addShapedRecipe(JourneyItems.concentratedBlood, "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.blood);
		addShapedRecipe(JourneyItems.boilKey, "dgd", "did", "dgd", 'd', JourneyItems.flamingSpring, 'i', JourneyItems.flamingSprocket, 'g', Items.GOLD_INGOT);
		addShapedRecipe(new ItemStack(JourneyItems.essenceArrow, 4), "d", "i", "g", 'd', Items.IRON_INGOT, 'i', JourneyItems.stoneStick, 'g', JourneyItems.rocFeather);
		addShapedRecipe(JourneyWeapons.withicHammer, "ddd", "did", " i ", 'd', JourneyItems.hellcrustIngot, 'i', JourneyItems.withicSpine);
		addShapedRecipe(JourneyWeapons.nethicHammer, "jdj", "did", " i ", 'd', JourneyBlocks.hellstoneBlock, 'i', JourneyItems.hellstoneClump, 'j', JourneyItems.flamingSpring);
		/*
		 * addShapedRecipe(new ItemStack(JourneyItems.pocketCrafting, 1),
		 * new Object[] { "ddd", "did", "ddd", 'd',
		 * JourneyItems.flamingHide, 'i', JourneyItems.concentratedBlood });
		 */
		addShapelessRecipe(new ItemStack(JourneyItems.demonicDust, 5), JourneyItems.demonicBone);
		addShapelessRecipe(new ItemStack(JourneyItems.smithstonedust, 4), JourneyItems.smithstone);
		addShapelessRecipe(new ItemStack(JourneyItems.bleedstonedust, 4), JourneyItems.bleedstone);
		addShapelessRecipe(JourneyItems.nethicgemstone, JourneyItems.bleedstonedust, JourneyItems.smithstonedust);
		addShapelessRecipe(JourneyItems.darkGem, JourneyItems.shadiumIngot, Items.DIAMOND);

		addOPFoodRecipes(JourneyConsumables.goldenPotato, JourneyConsumables.goldenPotatoOP, Items.POTATO);
		addOPFoodRecipes(JourneyConsumables.goldenWing, JourneyConsumables.goldenWingOP, JourneyConsumables.rocMeat);

		addShapedRecipe(JourneyItems.hellstoneClump, "iii", 'i', JourneyItems.hellstoneIngot);
		addShapedRecipe(JourneyItems.shadiumClump, "iii", 'i', JourneyItems.shadiumIngot);
		addShapedRecipe(JourneyItems.luniumClump, "iii", 'i', JourneyItems.luniumIngot);
		addShapelessRecipe(JourneyItems.spawnerClump, JourneyItems.shadiumClump, JourneyItems.luniumClump, JourneyItems.hellstoneClump);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2), JourneyBlocks.glowshroomRed);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2), JourneyBlocks.glowshroomGreen);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2), JourneyBlocks.glowshroomBlue);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), JourneyBlocks.tallGlowshroomRed);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), JourneyBlocks.tallGlowshroomGreen);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), JourneyBlocks.tallGlowshroomBlue);
		addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), JourneyConsumables.glowshroom);
		addShapelessRecipe(new ItemStack(JourneyCrops.floroSeeds, 4), JourneyConsumables.floroPedal);
		addShapelessRecipe(new ItemStack(JourneyItems.eucaPortalGem, 10), JourneyItems.eucaPortalPiece, JourneyItems.eucaPortalPiece_0, JourneyItems.eucaPortalPiece_1);
		addWoodRecipes(JourneyBlocks.eucaGoldLog, JourneyBlocks.goldEucaPlank, JourneyBlocks.eucaGoldStairs, 0, true);
		addWoodRecipes(JourneyBlocks.depthsLog, JourneyBlocks.depthsPlank, JourneyBlocks.depthsPlank, 1, true);
		addWoodRecipes(JourneyBlocks.corbaLog, JourneyBlocks.corbaPlank, null, 1, true);
		addWoodRecipes(JourneyBlocks.frozenLog, JourneyBlocks.frozenPlanks, null, 1, true);//TODO Add stairs

		addShapedRecipe(JourneyArmory.blazehornHelmet, "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
		addShapedRecipe(JourneyArmory.blazehornChest, "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
		addShapedRecipe(JourneyArmory.blazehornLegs, "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
		addShapedRecipe(JourneyArmory.blazehornBoots, "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);

		addShapedRecipe(JourneyArmory.fireboundHelmet, "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
		addShapedRecipe(JourneyArmory.fireboundChest, "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
		addShapedRecipe(JourneyArmory.fireboundLegs, "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
		addShapedRecipe(JourneyArmory.fireboundBoots, "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);

		addShapedRecipe(JourneyArmory.bloodcrustHelmet, "idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
		addShapedRecipe(JourneyArmory.bloodcrustChest, "i i", "did", "idi", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
		addShapedRecipe(JourneyArmory.bloodcrustLegs, "idi", "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
		addShapedRecipe(JourneyArmory.bloodcrustBoots, "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);

		addShapedRecipe(JourneyArmory.bleedrockHelmet, "idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
		addShapedRecipe(JourneyArmory.bleedrockChest, "i i", "did", "idi", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
		addShapedRecipe(JourneyArmory.bleedrockLegs, "idi", "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
		addShapedRecipe(JourneyArmory.bleedrockBoots, "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
		addShapelessRecipe(JourneyArmory.woodMultiTool, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE, Items.WOODEN_AXE);
		addShapelessRecipe(JourneyArmory.stoneMultiTool, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_HOE, Items.STONE_AXE);
		addShapelessRecipe(JourneyArmory.ironMultiTool, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_AXE);
		addShapelessRecipe(JourneyArmory.diamondMultiTool, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE, Items.DIAMOND_AXE);

		addDefaultRecipes(JourneyBlocks.celestiumOre, JourneyItems.celestiumIngot, JourneyBlocks.celestiumBlock,
				JourneyArmory.celestiumAxe, JourneyArmory.celestiumPickaxe, JourneyArmory.celestiumShovel,
				JourneyArmory.celestiumHoe, JourneyWeapons.celestiumSword, JourneyArmory.celestiumMultiTool,
				JourneyArmory.celestiumHelmet, JourneyArmory.celestiumChest, JourneyArmory.celestiumLegs,
				JourneyArmory.celestiumBoots, JourneyItems.celestiumDust);
		addDefaultRecipes(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneIngot, JourneyBlocks.hellstoneBlock,
				JourneyArmory.hellstoneAxe, JourneyArmory.hellstonePickaxe, JourneyArmory.hellstoneShovel,
				JourneyArmory.hellstoneHoe, JourneyWeapons.hellstoneSword, JourneyArmory.hellstoneMultiTool,
				JourneyArmory.hellstoneHelmet, JourneyArmory.hellstoneChest, JourneyArmory.hellstoneLegs,
				JourneyArmory.hellstoneBoots, JourneyItems.hellstoneDust);
		addDefaultRecipes(JourneyBlocks.flairiumOre, JourneyItems.flairiumIngot, JourneyBlocks.flairiumBlock,
				JourneyArmory.flairiumAxe, JourneyArmory.flairiumPickaxe, JourneyArmory.flairiumShovel,
				JourneyArmory.flairiumHoe, JourneyWeapons.flairiumSword, JourneyArmory.flairiumMultiTool,
				JourneyArmory.flairiumHelmet, JourneyArmory.flairiumChest, JourneyArmory.flairiumLegs,
				JourneyArmory.flairiumBoots, JourneyItems.flairiumDust);
		addDefaultRecipes(JourneyBlocks.desOre, JourneyItems.desIngot, JourneyBlocks.desBlock, JourneyArmory.desAxe,
				JourneyArmory.desPickaxe, JourneyArmory.desShovel, JourneyArmory.desHoe, JourneyWeapons.desSword,
				JourneyArmory.desMultiTool, null, null, null, null, null);
		addDefaultRecipes(JourneyBlocks.shadiumOre, JourneyItems.shadiumIngot, JourneyBlocks.shadiumBlock,
				JourneyArmory.shadiumAxe, JourneyArmory.shadiumPickaxe, JourneyArmory.shadiumShovel,
				JourneyArmory.shadiumHoe, JourneyWeapons.shadiumSword, JourneyArmory.shadiumMultiTool,
				JourneyArmory.shadiumHelmet, JourneyArmory.shadiumChest, JourneyArmory.shadiumLegs,
				JourneyArmory.shadiumBoots, JourneyItems.shadiumDust);
		addDefaultRecipes(JourneyBlocks.luniumOre, JourneyItems.luniumIngot, JourneyBlocks.luniumBlock, JourneyArmory.luniumAxe,
				JourneyArmory.luniumPickaxe, JourneyArmory.luniumShovel, JourneyArmory.luniumHoe,
				JourneyWeapons.luniumSword, JourneyArmory.luniumMultiTool, JourneyArmory.luniumHelmet,
				JourneyArmory.luniumChest, JourneyArmory.luniumLegs, JourneyArmory.luniumBoots,
				JourneyItems.luniumDust);
		addDefaultRecipes(JourneyBlocks.sapphireOre, JourneyItems.sapphire, JourneyBlocks.sapphireBlock,
				JourneyArmory.sapphireAxe, JourneyArmory.sapphirePickaxe, JourneyArmory.sapphireShovel,
				JourneyArmory.sapphireHoe, JourneyWeapons.sapphireSword, JourneyArmory.sapphireMultiTool,
				JourneyArmory.sapphireHelmet, JourneyArmory.sapphireChest, JourneyArmory.sapphireLegs,
				JourneyArmory.sapphireBoots, JourneyItems.sapphireDust);
		addDefaultRecipes(JourneyBlocks.gorbiteOre, JourneyItems.gorbiteGem, JourneyBlocks.gorbiteBlock,
				JourneyArmory.gorbiteAxe, JourneyArmory.gorbitePickaxe, JourneyArmory.gorbiteShovel,
				JourneyArmory.gorbiteHoe, JourneyWeapons.gorbiteSword, JourneyArmory.gorbiteMultiTool,
				JourneyArmory.gorbiteHelmet, JourneyArmory.gorbiteChest, JourneyArmory.gorbiteLegs,
				JourneyArmory.gorbiteBoots, JourneyItems.gorbiteDust);
		addDefaultRecipes(JourneyBlocks.orbaditeOre, JourneyItems.orbaditeIngot, JourneyBlocks.orbaditeBlock,
				JourneyArmory.orbaditeAxe, JourneyArmory.orbaditePickaxe, JourneyArmory.orbaditeShovel,
				JourneyArmory.orbaditeHoe, JourneyWeapons.orbaditeSword, JourneyArmory.orbaditeMultiTool,
				JourneyArmory.orbaditeHelmet, JourneyArmory.orbaditeChest, JourneyArmory.orbaditeLegs,
				JourneyArmory.orbaditeBoots, JourneyItems.orbaditeDust);
		addDefaultRecipes(JourneyBlocks.koriteOre, JourneyItems.koriteIngot, JourneyBlocks.koriteBlock, JourneyArmory.koriteAxe,
				JourneyArmory.koritePickaxe, JourneyArmory.koriteShovel, JourneyArmory.koriteHoe,
				JourneyWeapons.koriteSword, JourneyArmory.koriteMultiTool, null, null, null, null, null);
		addDefaultRecipes(JourneyBlocks.storonOre, JourneyItems.storonIngot, JourneyBlocks.storonBlock, JourneyArmory.storonAxe,
				JourneyArmory.storonPickaxe, JourneyArmory.storonShovel, JourneyArmory.storonHoe,
				JourneyWeapons.storonSword, JourneyArmory.storonMultiTool, null, null, null, null, null);
		addDefaultRecipes(JourneyBlocks.mekyumOre, JourneyItems.mekyumIngot, JourneyBlocks.mekyumBlock, JourneyArmory.mekyumAxe,
				JourneyArmory.mekyumPickaxe, JourneyArmory.mekyumShovel, JourneyArmory.mekyumHoe,
				JourneyWeapons.mekyumSword, JourneyArmory.mekyumMultiTool, null, null, null, null, null);
		addDefaultRecipes(null, JourneyItems.nethicgemstone, JourneyBlocks.nethicGemstoneBlock, JourneyArmory.nethicAxe,
				JourneyArmory.nethicPickaxe, JourneyArmory.nethicShovel, null,
				null, null, null, null, null, null, null);
		//addDefaultRecipes(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null,
		// null, null, null, null, null, null, i.ashDust);

		addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16),
				"ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall);

		addSwordRecipe(JourneyWeapons.withicBlade, JourneyItems.withicDust, JourneyItems.hellcrustIngot);
		addSwordRecipe(JourneyWeapons.reinforcedStoneSword, JourneyItems.reinforcedStoneIngot, JourneyItems.stoneStick);
		addSwordRecipe(JourneyWeapons.reinforcedCrystalSword, JourneyItems.reinforcedCrystalIngot, JourneyItems.stoneStick);
		addSwordRecipe(JourneyWeapons.pedalSword, JourneyConsumables.floroPedal, JourneyItems.stoneClump);
		addSwordRecipe(JourneyWeapons.crystalBlade, JourneyItems.caveCrystal, Item.getItemFromBlock(Blocks.STONE));
		addSwordRecipe(JourneyWeapons.demonicSword, JourneyItems.demonicBone, JourneyItems.demonicDust);
		addBowRecipe(JourneyWeapons.flameBow, Items.FIRE_CHARGE, Items.STRING);

		add3x3CompactingRecipes(JourneyBlocks.iridiumBlock, JourneyItems.iridium, true);
		add3x3CompactingRecipes(JourneyBlocks.ashualBlock, JourneyItems.ash, true);
		add3x3CompactingRecipes(JourneyBlocks.blaziumBlock, JourneyItems.blazium, true);
		add3x3CompactingRecipes(JourneyBlocks.enderilliumBlock, JourneyItems.enderilliumShard, true);
		add3x3CompactingRecipes(JourneyBlocks.luniteBlock, JourneyItems.luniteChunk, true);

		add2x2CompactingRecipes(JourneyBlocks.smithstone, JourneyItems.smithstone, true);
		add2x2CompactingRecipes(JourneyBlocks.bleedstone, JourneyItems.bleedstone, true);

		if (GENERATOR != null) {
			GENERATOR.generateConstants();
		}
	}

	public static void initFurnaceRecipes() {
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(JourneyBlocks.smoothGlass), 1f);
		GameRegistry.addSmelting(JourneyItems.spawnerClump, new ItemStack(JourneyItems.spawnerBar), 1.0F);
		GameRegistry.addSmelting(JourneyConsumables.flamingBeef, new ItemStack(JourneyConsumables.flamingBeefCooked), 0.5F);
		GameRegistry.addSmelting(JourneyItems.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
		GameRegistry.addSmelting(JourneyItems.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 1.5F);
		GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 1.0F);
		GameRegistry.addSmelting(Items.EGG, new ItemStack(JourneyConsumables.friedEgg), 0.5F);
		GameRegistry.addSmelting(JourneyConsumables.rocMeat, new ItemStack(JourneyConsumables.cookedRocMeat), 0.5F);
		GameRegistry.addSmelting(JourneyConsumables.ghastTentacle, new ItemStack(JourneyConsumables.friedGhastTentacale), 0.5F);
		GameRegistry.addSmelting(JourneyConsumables.flamingGhastTentacle, new ItemStack(JourneyConsumables.friedFlamingGhastTentacale), 0.5F);
		GameRegistry.addSmelting(JourneyConsumables.breathing_fungus, new ItemStack(JourneyConsumables.breathing_fungus_cooked), 0.5F);
	}

	private static void addShapedRecipe(Block b, Object... params) {
		addShapedRecipe(Item.getItemFromBlock(b), params);
	}

	private static void addShapedRecipe(Item output, Object... params) {
		addShapedRecipe(new ItemStack(output), params);
	}

	private static void addShapedRecipe(ItemStack output, Object... params) {
		if (GENERATOR != null) {
			GENERATOR.genShapedRecipe(output, params);
		}
	}

	private static void addShapelessRecipe(Block output, Object... params) {
		addShapelessRecipe(Item.getItemFromBlock(output), params);
	}

	private static void addShapelessRecipe(Item output, Object... params) {
		addShapelessRecipe(new ItemStack(output, 1), params);
	}

	private static void addShapelessRecipe(ItemStack output, Object... params) {
		if (GENERATOR != null) {
			GENERATOR.genShapelessRecipe(output, params);
		}
	}

	private static void add3x3CompactingRecipes(Block made, Item used, boolean addReverseRecipe) {
		addShapedRecipe(made, "iii", "iii", "iii", 'i', used);
		if (addReverseRecipe) addShapelessRecipe(new ItemStack(used, 9), made);
	}

	private static void add2x2CompactingRecipes(Block made, Item used, boolean addReverseRecipe) {
		addShapedRecipe(made, "ii", "ii", 'i', used);
		if (addReverseRecipe) addShapelessRecipe(new ItemStack(used, 4), made);
	}

	private static void addOPFoodRecipes(Item nonOP, Item OP, Item base) {
		addShapedRecipe(new ItemStack(nonOP), "iii", "ibi", "iii", 'i', Items.GOLD_INGOT, 'b', base);
		addShapedRecipe(new ItemStack(OP), "iii", "ibi", "iii", 'i', Blocks.GOLD_BLOCK, 'b', base);
	}

	private static void addFurnaceRecipes(Block stone) {
		addShapedRecipe(new ItemStack(Blocks.FURNACE), "iii", "i i", "iii", 'i', stone);
	}

	private static void addWoodRecipes(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		if (log != null && plank != null) addShapelessRecipe(new ItemStack(plank, 4), log);
		if (stair != null && plank != null) addShapedRecipe(new ItemStack(stair, 4), "i  ", "ii ", "iii", 'i', plank);
		if (plank != null) addShapedRecipe(new ItemStack(Items.STICK, 4), "i", "i", 'i', plank);
		if (plank != null) addShapedRecipe(new ItemStack(Blocks.CRAFTING_TABLE), "ii", "ii", 'i', plank);
		if (smelt) GameRegistry.addSmelting(log, new ItemStack(Items.COAL), 0.5F);
	}

	private static void addDefaultRecipes(Block ore, @NotNull Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword,
	                                      Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		Preconditions.checkNotNull(ingot);

		if (JITL.IN_JOURNEY_DEV) {
			if (axe != null) addAxeRecipe(axe, ingot, JourneyItems.obsidianRod);
			if (pick != null) addPickaxeRecipe(pick, ingot, JourneyItems.obsidianRod);
			if (shovel != null) addShovelRecipe(shovel, ingot, JourneyItems.obsidianRod);
			if (hoe != null) addHoeRecipe(hoe, ingot, JourneyItems.obsidianRod);
			if (sword != null)
				addShapedRecipe(new ItemStack(sword), "b", "b", "s", 'b', block, 's', JourneyItems.obsidianRod);
			if (block != null) add3x3CompactingRecipes(block, ingot, true);
			if (helmet != null) addHelmetRecipe(helmet, ingot);
			if (chest != null) addChestplateRecipe(chest, ingot);
			if (legs != null) addLeggingsRecipe(legs, ingot);
			if (boots != null) addBootsRecipe(boots, ingot);
			if (multiTool != null) addShapelessRecipe(new ItemStack(multiTool), pick, shovel, hoe, axe);
		}

		if (ore != null) GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if (dust != null) GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}

	private static void addAxeRecipe(Item axe, Item ingot, Item stick) {
		addShapedRecipe(axe, " ii", " si", " s ", 'i', ingot, 's', stick);
	}

	private static void addPickaxeRecipe(Item pick, Item ingot, Item stick) {
		addShapedRecipe(pick, "iii", " s ", " s ", 'i', ingot, 's', stick);
	}

	private static void addShovelRecipe(Item shovel, Item ingot, Item stick) {
		addShapedRecipe(shovel, " i ", " s ", " s ", 'i', ingot, 's', stick);
	}

	private static void addSwordRecipe(Item sword, Item ingot, Item stick) {
		addShapedRecipe(sword, " i ", " i ", " s ", 'i', ingot, 's', stick);
	}

	private static void addBowRecipe(Item bow, Item base, Item bowstring) {
		addShapedRecipe(bow, " fs", "f s", " fs", 'f', base, 's', bowstring);
	}

	private static void addHoeRecipe(Item hoe, Item ingot, Item stick) {
		addShapedRecipe(hoe, " ii", " s ", " s ", 'i', ingot, 's', stick);
	}

	private static void addHelmetRecipe(Item helmet, Item ingot) {
		addShapedRecipe(helmet, "iii", "i i", 'i', ingot);
	}

	private static void addChestplateRecipe(Item chest, Item ingot) {
		addShapedRecipe(chest, "i i", "iii", "iii", 'i', ingot);
	}

	private static void addLeggingsRecipe(Item legs, Item ingot) {
		addShapedRecipe(legs, "iii", "i i", "i i", 'i', ingot);
	}

	private static void addBootsRecipe(Item boots, Item ingot) {
		addShapedRecipe(boots, "i i", "i i", 'i', ingot);
	}
}