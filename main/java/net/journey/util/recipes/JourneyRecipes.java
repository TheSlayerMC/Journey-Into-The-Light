package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyCrops;
import net.journey.JourneyItems;
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
			addShapedRecipe(new ItemStack(JourneyItems.demonicBomb, 16),
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
			addShapedRecipe(new ItemStack(JourneyItems.withicHammer, 1), new Object[] { "ddd", "did", " i ", 'd',
					JourneyItems.hellcrustIngot, 'i', JourneyItems.withicSpine });
			addShapedRecipe(new ItemStack(JourneyItems.nethicHammer, 1), new Object[] { "jdj", "did", " i ", 'd',
					JourneyBlocks.hellstoneBlock, 'i', JourneyItems.hellstoneClump, 'j', JourneyItems.flamingSpring });
			/*
			 * addShapedRecipe(new ItemStack(JourneyItems.pocketCrafting, 1),
			 * new Object[] { "ddd", "did", "ddd", 'd',
			 * JourneyItems.flamingHide, 'i', JourneyItems.concentratedBlood });
			 */
			addShapelessRecipe(new ItemStack(JourneyItems.demonicDust, 5), new Object[] { JourneyItems.demonicBone });

			addOPFood(JourneyItems.goldenPork, JourneyItems.goldenPorkOP, Items.PORKCHOP);
			addOPFood(JourneyItems.goldenSteak, JourneyItems.goldenSteakOP, Items.BEEF);
			addOPFood(JourneyItems.goldenPotato, JourneyItems.goldenPotatoOP, Items.POTATO);
			addOPFood(JourneyItems.goldenFish, JourneyItems.goldenFishOP, Items.FISH);
			addOPFood(JourneyItems.goldenChicken, JourneyItems.goldenChickenOP, Items.CHICKEN);
			addOPFood(JourneyItems.goldenRabbit, JourneyItems.goldenRabbitOP, Items.RABBIT);
			addOPFood(JourneyItems.goldenMutton, JourneyItems.goldenMuttonOP, Items.MUTTON);
			addOPFood(JourneyItems.goldenWing, JourneyItems.goldenWingOP, JourneyItems.rocMeat);

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
					new Object[] { JourneyItems.glowshroom });
			addShapelessRecipe(new ItemStack(JourneyCrops.floroSeeds, 4), new Object[] { JourneyItems.floroPedal });
			addShapelessRecipe(new ItemStack(JourneyItems.eucaPortalGem, 10), new Object[] {
					JourneyItems.eucaPortalPiece, JourneyItems.eucaPortalPiece_0, JourneyItems.eucaPortalPiece_1 });
			addWood(JourneyBlocks.eucaGoldLog, JourneyBlocks.goldEucaPlank, JourneyBlocks.eucaGoldStairs, 0, true);
			addWood(JourneyBlocks.depthsLog, JourneyBlocks.depthsPlank, JourneyBlocks.depthsPlank, 1, true);
			addWood(JourneyBlocks.corbaLog, JourneyBlocks.corbaPlank, JourneyBlocks.corbaLog, 1, true);
			addWood(JourneyBlocks.frozenBark, JourneyBlocks.frozenPlanks, JourneyBlocks.corbaLog, 1, true);

			addShapedRecipe(new ItemStack(JourneyItems.blazehornHelmet),
					new Object[] { "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyItems.blazehornChest),
					new Object[] { "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyItems.blazehornLegs),
					new Object[] { "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyItems.blazehornBoots),
					new Object[] { "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });

			addShapedRecipe(new ItemStack(JourneyItems.fireboundHelmet),
					new Object[] { "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock });
			addShapedRecipe(new ItemStack(JourneyItems.fireboundChest),
					new Object[] { "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyItems.fireboundLegs),
					new Object[] { "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn });
			addShapedRecipe(new ItemStack(JourneyItems.fireboundBoots),
					new Object[] { "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock });

			addShapedRecipe(new ItemStack(JourneyItems.bloodcrustHelmet),
					new Object[] { "idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyItems.bloodcrustChest), new Object[] { "i i", "did", "idi", 'd',
					JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyItems.bloodcrustLegs), new Object[] { "idi", "d d", "i i", 'd',
					JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyItems.bloodcrustBoots),
					new Object[] { "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot });

			addShapedRecipe(new ItemStack(JourneyItems.bleedrockHelmet),
					new Object[] { "idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyItems.bleedrockChest), new Object[] { "i i", "did", "idi", 'd',
					JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyItems.bleedrockLegs), new Object[] { "idi", "d d", "i i", 'd',
					JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapedRecipe(new ItemStack(JourneyItems.bleedrockBoots),
					new Object[] { "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood });
			addShapelessRecipe(new ItemStack(JourneyItems.woodMultiTool),
					new Object[] { Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE, Items.WOODEN_AXE });
			addShapelessRecipe(new ItemStack(JourneyItems.stoneMultiTool),
					new Object[] { Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_HOE, Items.STONE_AXE });
			addShapelessRecipe(new ItemStack(JourneyItems.ironMultiTool),
					new Object[] { Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_AXE });
			addShapelessRecipe(new ItemStack(JourneyItems.diamondMultiTool),
					new Object[] { Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE, Items.DIAMOND_AXE });

			addOre(JourneyBlocks.celestiumOre, JourneyItems.celestiumIngot, JourneyBlocks.celestiumBlock,
					JourneyItems.celestiumAxe, JourneyItems.celestiumPickaxe, JourneyItems.celestiumShovel,
					JourneyItems.celestiumHoe, JourneyItems.celestiumSword, JourneyItems.celestiumMultiTool,
					JourneyItems.celestiumHelmet, JourneyItems.celestiumChest, JourneyItems.celestiumLegs,
					JourneyItems.celestiumBoots, JourneyItems.celestiumDust);
			addOre(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneIngot, JourneyBlocks.hellstoneBlock,
					JourneyItems.hellstoneAxe, JourneyItems.hellstonePickaxe, JourneyItems.hellstoneShovel,
					JourneyItems.hellstoneHoe, JourneyItems.hellstoneSword, JourneyItems.hellstoneMultiTool,
					JourneyItems.hellstoneHelmet, JourneyItems.hellstoneChest, JourneyItems.hellstoneLegs,
					JourneyItems.hellstoneBoots, JourneyItems.hellstoneDust);
			addOre(JourneyBlocks.flairiumOre, JourneyItems.flairiumIngot, JourneyBlocks.flairiumBlock,
					JourneyItems.flairiumAxe, JourneyItems.flairiumPickaxe, JourneyItems.flairiumShovel,
					JourneyItems.flairiumHoe, JourneyItems.flairiumSword, JourneyItems.flairiumMultiTool,
					JourneyItems.flairiumHelmet, JourneyItems.flairiumChest, JourneyItems.flairiumLegs,
					JourneyItems.flairiumBoots, JourneyItems.flairiumDust);
			addOre(JourneyBlocks.desOre, JourneyItems.desIngot, JourneyBlocks.desBlock, JourneyItems.desAxe,
					JourneyItems.desPickaxe, JourneyItems.desShovel, JourneyItems.desHoe, JourneyItems.desSword,
					JourneyItems.desMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.shadiumOre, JourneyItems.shadiumIngot, JourneyBlocks.shadiumBlock,
					JourneyItems.shadiumAxe, JourneyItems.shadiumPickaxe, JourneyItems.shadiumShovel,
					JourneyItems.shadiumHoe, JourneyItems.shadiumSword, JourneyItems.shadiumMultiTool,
					JourneyItems.shadiumHelmet, JourneyItems.shadiumChest, JourneyItems.shadiumLegs,
					JourneyItems.shadiumBoots, JourneyItems.shadiumDust);
			addOre(JourneyBlocks.luniumOre, JourneyItems.luniumIngot, JourneyBlocks.luniumBlock, JourneyItems.luniumAxe,
					JourneyItems.luniumPickaxe, JourneyItems.luniumShovel, JourneyItems.luniumHoe,
					JourneyItems.luniumSword, JourneyItems.luniumMultiTool, JourneyItems.luniumHelmet,
					JourneyItems.luniumChest, JourneyItems.luniumLegs, JourneyItems.luniumBoots,
					JourneyItems.luniumDust);
			addOre(JourneyBlocks.sapphireOre, JourneyItems.sapphire, JourneyBlocks.sapphireBlock,
					JourneyItems.sapphireAxe, JourneyItems.sapphirePickaxe, JourneyItems.sapphireShovel,
					JourneyItems.sapphireHoe, JourneyItems.sapphireSword, JourneyItems.sapphireMultiTool,
					JourneyItems.sapphireHelmet, JourneyItems.sapphireChest, JourneyItems.sapphireLegs,
					JourneyItems.sapphireBoots, JourneyItems.sapphireDust);
			addOre(JourneyBlocks.gorbiteOre, JourneyItems.gorbiteGem, JourneyBlocks.gorbiteBlock,
					JourneyItems.gorbiteAxe, JourneyItems.gorbitePickaxe, JourneyItems.gorbiteShovel,
					JourneyItems.gorbiteHoe, JourneyItems.gorbiteSword, JourneyItems.gorbiteMultiTool,
					JourneyItems.gorbiteHelmet, JourneyItems.gorbiteChest, JourneyItems.gorbiteLegs,
					JourneyItems.gorbiteBoots, JourneyItems.gorbiteDust);
			addOre(JourneyBlocks.orbaditeOre, JourneyItems.orbaditeIngot, JourneyBlocks.orbaditeBlock,
					JourneyItems.orbaditeAxe, JourneyItems.orbaditePickaxe, JourneyItems.orbaditeShovel,
					JourneyItems.orbaditeHoe, JourneyItems.orbaditeSword, JourneyItems.orbaditeMultiTool,
					JourneyItems.orbaditeHelmet, JourneyItems.orbaditeChest, JourneyItems.orbaditeLegs,
					JourneyItems.orbaditeBoots, JourneyItems.orbaditeDust);
			addOre(JourneyBlocks.koriteOre, JourneyItems.koriteIngot, JourneyBlocks.koriteBlock, JourneyItems.koriteAxe,
					JourneyItems.koritePickaxe, JourneyItems.koriteShovel, JourneyItems.koriteHoe,
					JourneyItems.koriteSword, JourneyItems.koriteMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.storonOre, JourneyItems.storonIngot, JourneyBlocks.storonBlock, JourneyItems.storonAxe,
					JourneyItems.storonPickaxe, JourneyItems.storonShovel, JourneyItems.storonHoe,
					JourneyItems.storonSword, JourneyItems.storonMultiTool, null, null, null, null, null);
			addOre(JourneyBlocks.mekyumOre, JourneyItems.mekyumIngot, JourneyBlocks.mekyumBlock, JourneyItems.mekyumAxe,
					JourneyItems.mekyumPickaxe, JourneyItems.mekyumShovel, JourneyItems.mekyumHoe,
					JourneyItems.mekyumSword, JourneyItems.mekyumMultiTool, null, null, null, null, null);
			// addOre(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null,
			// null, null, null, null, null, null, i.ashDust);
			
			addShapedRecipe(new ItemStack(JourneyItems.demonicBomb, 16),
					new Object[] { "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall });
			addShapedRecipe(new ItemStack(JourneyItems.withicBlade),
					new Object[] { "i", "i", "d", 'd', JourneyItems.withicDust, 'i', JourneyItems.hellcrustIngot });
			addShapedRecipe(new ItemStack(JourneyItems.reinforcedStoneSword), new Object[] { "d", "d", "i", 'd',
					JourneyItems.reinforcedStoneIngot, 'i', JourneyItems.stoneStick });
			addShapedRecipe(new ItemStack(JourneyItems.reinforcedCrystalSword), new Object[] { "d", "d", "i", 'd',
					JourneyItems.reinforcedCrystalIngot, 'i', JourneyItems.stoneStick });
			addShapedRecipe(new ItemStack(JourneyItems.pedalSword),
					new Object[] { "d", "d", "i", 'd', JourneyItems.floroPedal, 'i', JourneyItems.stoneClump });
			addShapedRecipe(new ItemStack(JourneyItems.crystalBlade),
					new Object[] { "d", "d", "i", 'd', JourneyItems.caveCrystal, 'i', Blocks.STONE });
			addShapedRecipe(new ItemStack(JourneyItems.flameBow),
					new Object[] { " fs", "f s", " fs", 'f', Items.FIRE_CHARGE, 's', Items.STRING });
			addShapedRecipe(new ItemStack(JourneyItems.demonicSword),
					new Object[] { "d", "d", "i", 'd', JourneyItems.demonicBone, 'i', JourneyItems.demonicDust });

			addSmelting(Blocks.GLASS, b.smoothGlass, 1);

			JourneyJSONGenerator.generateConstants();
		}
	}
}