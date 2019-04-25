package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.util.Config;
import net.journey.util.JourneyJSONGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
			addShapedRecipe(new ItemStack(JourneyBlocks.summoningTable, 1), new Object[] { "dsd",
					"iii", "iii", 'i', JourneyItems.shadiumIngot, 'd', Items.DIAMOND, 's', JourneyItems.sapphire });
			addShapedRecipe(new ItemStack(JourneyBlocks.boilingBars, 4),
					new Object[] { "ddd", "ddd", 'd', Items.BLAZE_ROD });
			addShapedRecipe(new ItemStack(JourneyBlocks.stoneCraftingTable, 1),
					new Object[] { "dd", "dd", 'd', Blocks.COBBLESTONE });
			addShapedRecipe(new ItemStack(JourneyBlocks.trophy, 1),
					new Object[] { "d d", "ddd", " d ", 'd', Items.GOLD_INGOT });
			addShapedRecipe(new ItemStack(JourneyBlocks.grindstone), new Object[] { " o ", "bib",
					"bbb", 'b', Blocks.BRICK_BLOCK, 'o', Blocks.OBSIDIAN, 'i', Items.IRON_INGOT });
			addShapedRecipe(new ItemStack(Blocks.WEB),
					new Object[] { "sss", "sss", "sss", 's', Items.STRING });
			addShapedRecipe(new ItemStack(JourneyBlocks.netherFurnace, 1),
					new Object[] { "ddd", "d d", "ddd", 'd', Blocks.NETHERRACK });
			JourneyJSONGenerator.generateConstants();
		}
	}
}