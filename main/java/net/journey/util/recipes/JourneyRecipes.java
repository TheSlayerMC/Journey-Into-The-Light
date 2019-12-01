package net.journey.util.recipes;

import net.journey.JourneyArmory;
import net.journey.JourneyBlocks;
import net.journey.JourneyConsumables;
import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.util.Config;
import net.journey.util.JourneyJSONGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class JourneyRecipes {

	public void addOredictRecipe(Block result, Object... materials) {
		if (Config.JSON) {
			JourneyJSONGenerator.addRecipe(new ItemStack(result, 1), materials);
		}
	}

	public void addOredictRecipe(Item result, Object... materials) {
		if (Config.JSON) {
			JourneyJSONGenerator.addRecipe(new ItemStack(result, 1), materials);
		}
	}

	public void addOredictRecipe(ItemStack result, Object... materials) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapedRecipe(result, materials);
		}
	}

	protected void addShapedRecipe(Block b, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapedRecipe(new ItemStack(b, 1), o);
		}
	}

	protected void addShapedRecipe(Item i, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapedRecipe(new ItemStack(i, 1), o);
		}
	}

	protected void addShapedRecipe(ItemStack i, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapedRecipe(i, o);
		}
	}

	protected void addShapelessRecipe(Block b, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapelessRecipe(new ItemStack(b, 1), o);
		}
	}

	protected void addShapelessRecipe(Item i, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapelessRecipe(new ItemStack(i, 1), o);
		}
	}

	protected void addShapelessRecipe(ItemStack i, Object... o) {
		if (Config.JSON) {
			JourneyJSONGenerator.addShapelessRecipe(i, o);
		}
	}

	protected void addSmelting(Block input, Block output, float XP) {
		if (Config.JSON) {
			JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);
		}
	}

	protected void addSmelting(Block input, Item output, float XP) {
		if (Config.JSON) {
			JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);
		}
	}

	protected void addSmelting(Item input, Block output, float XP) {
		if (Config.JSON) {
			JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);
		}
	}

	protected void addSmelting(Item input, Item output, float XP) {
		if (Config.JSON) {
			JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);
		}
	}

	protected void addSmelting(ItemStack input, ItemStack output, float XP) {
		if (Config.JSON) {
			JourneyJSONGenerator.addSmelting(input, output, XP);
		}
	}

	protected void addBlock(Block made, Item used) {
		addShapedRecipe(new ItemStack(made), new Object[] { "iii", "iii", "iii", 'i', used });
	}

	protected void addBlock(Block made, Block used) {
		addShapedRecipe(new ItemStack(made), new Object[] { "iii", "iii", "iii", 'i', used });
	}

	protected void addOPFood(Item nonOP, Item OP, Item base) {
		addShapedRecipe(new ItemStack(nonOP), new Object[] { "iii", "ibi", "iii", 'i', Items.GOLD_INGOT, 'b', base });
		addShapedRecipe(new ItemStack(OP), new Object[] { "iii", "ibi", "iii", 'i', Blocks.GOLD_BLOCK, 'b', base });
	}

	protected void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		addShapelessRecipe(new ItemStack(plank, 4), new Object[] { log });
		addShapedRecipe(new ItemStack(stair, 4), new Object[] { "i  ", "ii ", "iii", 'i', plank });
		addShapedRecipe(new ItemStack(Items.STICK, 4), new Object[] { "i", "i", 'i', plank });
		addShapedRecipe(new ItemStack(Blocks.CRAFTING_TABLE), new Object[] { "ii", "ii", 'i', plank });
		if (smelt)
			GameRegistry.addSmelting(log, new ItemStack(Items.COAL), 0.5F);
	}

	protected void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword,
			Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		addAxe(axe, ingot);
		addPickaxe(pick, ingot);
		addShovel(shovel, ingot);
		addHoe(hoe, ingot);
		addShapedRecipe(new ItemStack(sword), new Object[] { "b", "b", "s", 'b', block, 's', Items.STICK });
		addBlock(block, ingot);
		addHelmet(helmet, ingot);
		addChestplate(chest, ingot);
		addLeggings(legs, ingot);
		addBoots(boots, ingot);
		addShapelessRecipe(new ItemStack(multiTool), new Object[] { pick, shovel, hoe, axe });
		addShapelessRecipe(new ItemStack(ingot, 9), new Object[] { block });
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if (dust != null)
			GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}

	protected void addAxe(Item axe, Item ingot) {
		addShapedRecipe(new ItemStack(axe), new Object[] { " ii", " si", " s ", 'i', ingot, 's', JourneyItems.obsidianrod });
	}

	protected void addPickaxe(Item pick, Item ingot) {
		addShapedRecipe(new ItemStack(pick), new Object[] { "iii", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianrod });
	}

	protected void addShovel(Item shovel, Item ingot) {
		addShapedRecipe(new ItemStack(shovel), new Object[] { " i ", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianrod });
	}

	protected void addSword(Item sword, Item ingot) {
		addShapedRecipe(new ItemStack(sword), new Object[] { " i ", " i ", " s ", 'i', ingot, 's', JourneyItems.obsidianrod });
	}

	protected void addHoe(Item hoe, Item ingot) {
		addShapedRecipe(new ItemStack(hoe), new Object[] { " ii", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianrod });
	}

	protected void addHelmet(Item helmet, Item ingot) {
		addShapedRecipe(new ItemStack(helmet), new Object[] { "iii", "i i", 'i', ingot });
	}

	protected void addChestplate(Item chest, Item ingot) {
		addShapedRecipe(new ItemStack(chest), new Object[] { "i i", "iii", "iii", 'i', ingot });
	}

	protected void addLeggings(Item legs, Item ingot) {
		addShapedRecipe(new ItemStack(legs), new Object[] { "iii", "i i", "i i", 'i', ingot });
	}

	protected void addBoots(Item boots, Item ingot) {
		addShapedRecipe(new ItemStack(boots), new Object[] { "i i", "i i", 'i', ingot });
	}

	public JourneyRecipes() {
		if (SlayerAPI.DEVMODE) {
			JourneyBlocks b = new JourneyBlocks();
			JourneyItems i = new JourneyItems();
			addShapedRecipe(JourneyBlocks.greenGemBlock, "iii", "iii", "iii", 'i', JourneyItems.greenGem);
			addShapedRecipe(new ItemStack(JourneyBlocks.purpleGemBlock, 1),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.purpleGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.blueGemBlock, 1),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.blueGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.yellowGemBlock, 1),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.yellowGem });

			addShapedRecipe(new ItemStack(JourneyItems.greenGem, 9),
					new Object[] { "i", 'i', JourneyBlocks.greenGemBlock });
			addShapedRecipe(new ItemStack(JourneyItems.purpleGem, 9),
					new Object[] { "i", 'i', JourneyBlocks.purpleGemBlock });
			addShapedRecipe(new ItemStack(JourneyItems.blueGem, 9),
					new Object[] { "i", 'i', JourneyBlocks.blueGemBlock });
			addShapedRecipe(new ItemStack(JourneyItems.yellowGem, 9),
					new Object[] { "i", 'i', JourneyBlocks.yellowGemBlock });

			addShapedRecipe(new ItemStack(JourneyBlocks.igniter, 1),
					new Object[] { "iii", "jkj", "cfc", 'i', Blocks.NETHERRACK, 'j', Items.IRON_INGOT, 'k',
							JourneyBlocks.lavaRock, 'c', Blocks.COBBLESTONE, 'f', Items.FLINT_AND_STEEL });
			addShapedRecipe(new ItemStack(JourneyBlocks.eucaPortalFrame, 10),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.eucaPortalGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.depthsPortalFrame, 10),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.depthsPortalGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.cloudiaPortalFrame, 10),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.cloudiaPortalGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.corbaPortalFrame, 12),
					new Object[] { "iii", "iii", 'i', JourneyItems.corbaPortalGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.terraniaPortalFrame, 10),
					new Object[] { "iii", "iii", "iii", 'i', JourneyItems.terraniaPortalGem });
			addShapedRecipe(new ItemStack(JourneyBlocks.frozenPortalFrame, 10),
					new Object[] { "iii", "idi", "iii", 'i', Items.SNOWBALL, 'd', Items.DIAMOND });
			addShapedRecipe(new ItemStack(JourneyBlocks.summoningTable, 1), new Object[] { "dsd", "iii", "iii", 'i',
					JourneyItems.shadiumIngot, 'd', Items.DIAMOND, 's', JourneyItems.sapphire });
			addShapedRecipe(new ItemStack(JourneyBlocks.boilingBars, 4),
					new Object[] { "ddd", "ddd", 'd', Items.BLAZE_ROD });
			addShapedRecipe(new ItemStack(JourneyBlocks.stoneCraftingTable, 1),
					new Object[] { "dd", "dd", 'd', Blocks.COBBLESTONE });
			addShapedRecipe(new ItemStack(JourneyBlocks.trophy, 1),
					new Object[] { "d d", "ddd", " d ", 'd', Items.GOLD_INGOT });
			addShapedRecipe(new ItemStack(JourneyBlocks.grindstone), new Object[] { " o ", "bib", "bbb", 'b',
					Blocks.BRICK_BLOCK, 'o', Blocks.OBSIDIAN, 'i', Items.IRON_INGOT });
			addShapedRecipe(new ItemStack(Blocks.WEB), new Object[] { "sss", "sss", "sss", 's', Items.STRING });
			addShapedRecipe(new ItemStack(JourneyBlocks.netherFurnace, 1),
					new Object[] { "ddd", "d d", "ddd", 'd', Blocks.NETHERRACK });
			addShapedRecipe(new ItemStack(JourneyItems.flameCoin),
					new Object[] { "iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND });
			addShapedRecipe(new ItemStack(JourneyItems.hellcrustIngot),
					new Object[] { "iii", "idi", "iii", 'i', JourneyItems.ash, 'd', JourneyItems.hellstoneIngot });
			addShapedRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot),
					new Object[] { "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', Blocks.STONE });
			addShapedRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot),
					new Object[] { "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', JourneyItems.caveCrystal });
			addShapedRecipe(new ItemStack(JourneyItems.crystalBall), new Object[] { "idi", "ixi", 'i', Items.DIAMOND,
					'd', Items.ENDER_PEARL, 'x', JourneyItems.sapphire });
			addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16),
					new Object[] { "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall });
			addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 4),
					new Object[] { "ddd", "ddd", "ddd", 'd', JourneyItems.caveDust });
			addShapedRecipe(new ItemStack(JourneyItems.stoneStick, 16),
					new Object[] { "d", "d", "d", 'd', Blocks.STONE });
			addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16),
					new Object[] { "ddd", "ddd", "ddd", 'd', Blocks.STONE });
			addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16),
					new Object[] { "ddd", "ddd", "ddd", 'd', Blocks.COBBLESTONE });
			addShapedRecipe(new ItemStack(JourneyItems.withicSoul, 1),
					new Object[] { "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.withicDust });
			addShapedRecipe(new ItemStack(JourneyItems.concentratedBlood, 1),
					new Object[] { "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.blood });
			addShapedRecipe(new ItemStack(JourneyItems.boilKey, 1), new Object[] { "dgd", "did", "dgd", 'd',
					JourneyItems.flamingSpring, 'i', JourneyItems.flamingSprocket, 'g', Items.GOLD_INGOT });
			addShapedRecipe(new ItemStack(JourneyItems.essenceArrow, 4), new Object[] { "d", "i", "g", 'd',
					Items.IRON_INGOT, 'i', JourneyItems.stoneStick, 'g', JourneyItems.rocFeather });
			addShapedRecipe(new ItemStack(JourneyWeapons.withicHammer, 1), new Object[] { "ddd", "did", " i ", 'd',
					JourneyItems.hellcrustIngot, 'i', JourneyItems.withicSpine });
			addShapedRecipe(new ItemStack(JourneyWeapons.nethicHammer, 1), new Object[] { "jdj", "did", " i ", 'd',
					JourneyBlocks.hellstoneBlock, 'i', JourneyItems.hellstoneClump, 'j', JourneyItems.flamingSpring });
			/*
			 * addShapedRecipe(new ItemStack(JourneyItems.pocketCrafting, 1),
			 * new Object[] { "ddd", "did", "ddd", 'd',
			 * JourneyItems.flamingHide, 'i', JourneyItems.concentratedBlood });
			 */
			addShapelessRecipe(new ItemStack(JourneyItems.demonicDust, 5), new Object[] { JourneyItems.demonicBone });

			addOPFood(JourneyConsumables.goldenPork, JourneyConsumables.goldenPorkOP, Items.PORKCHOP);
			addOPFood(JourneyConsumables.goldenSteak, JourneyConsumables.goldenSteakOP, Items.BEEF);
			addOPFood(JourneyConsumables.goldenPotato, JourneyConsumables.goldenPotatoOP, Items.POTATO);
			addOPFood(JourneyConsumables.goldenFish, JourneyConsumables.goldenFishOP, Items.FISH);
			addOPFood(JourneyConsumables.goldenChicken, JourneyConsumables.goldenChickenOP, Items.CHICKEN);
			addOPFood(JourneyConsumables.goldenRabbit, JourneyConsumables.goldenRabbitOP, Items.RABBIT);
			addOPFood(JourneyConsumables.goldenMutton, JourneyConsumables.goldenMuttonOP, Items.MUTTON);
			addOPFood(JourneyConsumables.goldenWing, JourneyConsumables.goldenWingOP, JourneyConsumables.rocMeat);

			addShapedRecipe(new ItemStack(JourneyItems.hellstoneClump),
					new Object[] { "iii", 'i', JourneyItems.hellstoneIngot });
			addShapedRecipe(new ItemStack(JourneyItems.shadiumClump),
					new Object[] { "iii", 'i', JourneyItems.shadiumIngot });
			addShapedRecipe(new ItemStack(JourneyItems.luniumClump),
					new Object[] { "iii", 'i', JourneyItems.luniumIngot });
			addShapelessRecipe(new ItemStack(JourneyItems.spawnerClump),
					new Object[] { JourneyItems.shadiumClump, JourneyItems.luniumClump, JourneyItems.hellstoneClump });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.redGlowshroomTop });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.redGlowshroomBottom });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.blueGlowshroomTop });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.blueGlowshroomBottom });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.greenGlowshroomTop });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyBlocks.greenGlowshroomBottom });
			addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
					new Object[] { JourneyConsumables.glowshroom });
			addShapelessRecipe(new ItemStack(JourneyCrops.floroSeeds, 4), new Object[] { JourneyConsumables.floroPedal });
			addShapelessRecipe(new ItemStack(JourneyItems.eucaPortalGem, 10), new Object[] {
					JourneyItems.eucaPortalPiece, JourneyItems.eucaPortalPiece_0, JourneyItems.eucaPortalPiece_1 });
			addWood(JourneyBlocks.eucaGoldLog, JourneyBlocks.goldEucaPlank, JourneyBlocks.eucaGoldStairs, 0, true);
			addWood(JourneyBlocks.depthsLog, JourneyBlocks.depthsPlank, JourneyBlocks.depthsPlank, 1, true);
			addWood(JourneyBlocks.corbaLog, JourneyBlocks.corbaPlank, JourneyBlocks.corbaLog, 1, true);
			addWood(JourneyBlocks.frozenBark, JourneyBlocks.frozenPlanks, JourneyBlocks.corbaLog, 1, true);

			addShapedRecipe(new ItemStack(JourneyArmory.blazehornHelmet),
					new Object[] { "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyArmory.blazehornChest),
					new Object[] { "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyArmory.blazehornLegs),
					new Object[] { "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyArmory.blazehornBoots),
					new Object[] { "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });

			addShapedRecipe(new ItemStack(JourneyArmory.fireboundHelmet),
					new Object[] { "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock });
			addShapedRecipe(new ItemStack(JourneyArmory.fireboundChest),
					new Object[] { "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyArmory.fireboundLegs),
					new Object[] { "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyArmory.fireboundBoots),
					new Object[] { "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock });

			addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustHelmet),
					new Object[] { "idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustChest), new Object[] { "i i", "did", "idi", 'd',
					JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustLegs), new Object[] { "idi", "d d", "i i", 'd',
					JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustBoots),
					new Object[] { "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });

			addShapedRecipe(new ItemStack(JourneyArmory.bleedrockHelmet),
					new Object[] { "idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyArmory.bleedrockChest), new Object[] { "i i", "did", "idi", 'd',
					JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyArmory.bleedrockLegs), new Object[] { "idi", "d d", "i i", 'd',
					JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyArmory.bleedrockBoots),
					new Object[] { "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapelessRecipe(new ItemStack(JourneyArmory.woodMultiTool),
					new Object[] { Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE, Items.WOODEN_AXE });
			addShapelessRecipe(new ItemStack(JourneyArmory.stoneMultiTool),
					new Object[] { Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_HOE, Items.STONE_AXE });
			addShapelessRecipe(new ItemStack(JourneyArmory.ironMultiTool),
					new Object[] { Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_AXE });
			addShapelessRecipe(new ItemStack(JourneyArmory.diamondMultiTool),
					new Object[] { Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE, Items.DIAMOND_AXE });

			addOre(JourneyBlocks.celestiumOre, JourneyItems.celestiumIngot, JourneyBlocks.celestiumBlock,
					JourneyArmory.celestiumAxe, JourneyArmory.celestiumPickaxe, JourneyArmory.celestiumShovel,
					JourneyArmory.celestiumHoe, JourneyWeapons.celestiumSword, JourneyArmory.celestiumMultiTool,
					JourneyArmory.celestiumHelmet, JourneyArmory.celestiumChest, JourneyArmory.celestiumLegs,
					JourneyArmory.celestiumBoots, JourneyItems.celestiumDust);
			addOre(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneIngot, JourneyBlocks.hellstoneBlock,
					JourneyArmory.hellstoneAxe, JourneyArmory.hellstonePickaxe, JourneyArmory.hellstoneShovel,
					JourneyArmory.hellstoneHoe, JourneyWeapons.hellstoneSword, JourneyArmory.hellstoneMultiTool,
					JourneyArmory.hellstoneHelmet, JourneyArmory.hellstoneChest, JourneyArmory.hellstoneLegs,
					JourneyArmory.hellstoneBoots, JourneyItems.hellstoneDust);
			addOre(JourneyBlocks.flairiumOre, JourneyItems.flairiumIngot, JourneyBlocks.flairiumBlock,
					JourneyArmory.flairiumAxe, JourneyArmory.flairiumPickaxe, JourneyArmory.flairiumShovel,
					JourneyArmory.flairiumHoe, JourneyWeapons.flairiumSword, JourneyArmory.flairiumMultiTool,
					JourneyArmory.flairiumHelmet, JourneyArmory.flairiumChest, JourneyArmory.flairiumLegs,
					JourneyArmory.flairiumBoots, JourneyItems.flairiumDust);
			addOre(JourneyBlocks.desOre, JourneyItems.desIngot, JourneyBlocks.desBlock, JourneyArmory.desAxe,
					JourneyArmory.desPickaxe, JourneyArmory.desShovel, JourneyArmory.desHoe, JourneyWeapons.desSword,
					JourneyArmory.desMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.shadiumOre, JourneyItems.shadiumIngot, JourneyBlocks.shadiumBlock,
					JourneyArmory.shadiumAxe, JourneyArmory.shadiumPickaxe, JourneyArmory.shadiumShovel,
					JourneyArmory.shadiumHoe, JourneyWeapons.shadiumSword, JourneyArmory.shadiumMultiTool,
					JourneyArmory.shadiumHelmet, JourneyArmory.shadiumChest, JourneyArmory.shadiumLegs,
					JourneyArmory.shadiumBoots, JourneyItems.shadiumDust);
			addOre(JourneyBlocks.luniumOre, JourneyItems.luniumIngot, JourneyBlocks.luniumBlock, JourneyArmory.luniumAxe,
					JourneyArmory.luniumPickaxe, JourneyArmory.luniumShovel, JourneyArmory.luniumHoe,
					JourneyWeapons.luniumSword, JourneyArmory.luniumMultiTool, JourneyArmory.luniumHelmet,
					JourneyArmory.luniumChest, JourneyArmory.luniumLegs, JourneyArmory.luniumBoots,
					JourneyItems.luniumDust);
			addOre(JourneyBlocks.sapphireOre, JourneyItems.sapphire, JourneyBlocks.sapphireBlock,
					JourneyArmory.sapphireAxe, JourneyArmory.sapphirePickaxe, JourneyArmory.sapphireShovel,
					JourneyArmory.sapphireHoe, JourneyWeapons.sapphireSword, JourneyArmory.sapphireMultiTool,
					JourneyArmory.sapphireHelmet, JourneyArmory.sapphireChest, JourneyArmory.sapphireLegs,
					JourneyArmory.sapphireBoots, JourneyItems.sapphireDust);
			addOre(JourneyBlocks.gorbiteOre, JourneyItems.gorbiteGem, JourneyBlocks.gorbiteBlock,
					JourneyArmory.gorbiteAxe, JourneyArmory.gorbitePickaxe, JourneyArmory.gorbiteShovel,
					JourneyArmory.gorbiteHoe, JourneyWeapons.gorbiteSword, JourneyArmory.gorbiteMultiTool,
					JourneyArmory.gorbiteHelmet, JourneyArmory.gorbiteChest, JourneyArmory.gorbiteLegs,
					JourneyArmory.gorbiteBoots, JourneyItems.gorbiteDust);
			addOre(JourneyBlocks.orbaditeOre, JourneyItems.orbaditeIngot, JourneyBlocks.orbaditeBlock,
					JourneyArmory.orbaditeAxe, JourneyArmory.orbaditePickaxe, JourneyArmory.orbaditeShovel,
					JourneyArmory.orbaditeHoe, JourneyWeapons.orbaditeSword, JourneyArmory.orbaditeMultiTool,
					JourneyArmory.orbaditeHelmet, JourneyArmory.orbaditeChest, JourneyArmory.orbaditeLegs,
					JourneyArmory.orbaditeBoots, JourneyItems.orbaditeDust);
			addOre(JourneyBlocks.koriteOre, JourneyItems.koriteIngot, JourneyBlocks.koriteBlock, JourneyArmory.koriteAxe,
					JourneyArmory.koritePickaxe, JourneyArmory.koriteShovel, JourneyArmory.koriteHoe,
					JourneyWeapons.koriteSword, JourneyArmory.koriteMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.storonOre, JourneyItems.storonIngot, JourneyBlocks.storonBlock, JourneyArmory.storonAxe,
					JourneyArmory.storonPickaxe, JourneyArmory.storonShovel, JourneyArmory.storonHoe,
					JourneyWeapons.storonSword, JourneyArmory.storonMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.mekyumOre, JourneyItems.mekyumIngot, JourneyBlocks.mekyumBlock, JourneyArmory.mekyumAxe,
					JourneyArmory.mekyumPickaxe, JourneyArmory.mekyumShovel, JourneyArmory.mekyumHoe,
					JourneyWeapons.mekyumSword, JourneyArmory.mekyumMultiTool, null, null, null, null, null);
			addOre(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null,
			 null, null, null, null, null, null, i.ashDust);
			
			addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16),
					new Object[] { "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall });
			addShapedRecipe(new ItemStack(JourneyWeapons.withicBlade),
					new Object[] { "i", "i", "d", 'd', JourneyItems.withicDust, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyWeapons.reinforcedStoneSword), new Object[] { "d", "d", "i", 'd',
					JourneyItems.reinforcedStoneIngot, 'i', JourneyItems.stoneStick });
			addShapedRecipe(new ItemStack(JourneyWeapons.reinforcedCrystalSword), new Object[] { "d", "d", "i", 'd',
					JourneyItems.reinforcedCrystalIngot, 'i', JourneyItems.stoneStick });
			addShapedRecipe(new ItemStack(JourneyWeapons.pedalSword),
					new Object[] { "d", "d", "i", 'd', JourneyConsumables.floroPedal, 'i', JourneyItems.stoneClump });
			addShapedRecipe(new ItemStack(JourneyWeapons.crystalBlade),
					new Object[] { "d", "d", "i", 'd', JourneyItems.caveCrystal, 'i', Blocks.STONE });
			addShapedRecipe(new ItemStack(JourneyWeapons.flameBow),
					new Object[] { " fs", "f s", " fs", 'f', Items.FIRE_CHARGE, 's', Items.STRING });
			addShapedRecipe(new ItemStack(JourneyWeapons.demonicSword),
					new Object[] { "d", "d", "i", 'd', JourneyItems.demonicBone, 'i', JourneyItems.demonicDust });

			addSmelting(Blocks.GLASS, b.smoothGlass, 1);

			JourneyJSONGenerator.generateConstants();
		}
	}
}