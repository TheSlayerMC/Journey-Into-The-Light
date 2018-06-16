package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class JourneyBlockRecipes {

	public static void init() {
		initBlockCrafting();
	}

	public static void initBlockCrafting() {
		//JourneyBlocks b = new JourneyBlocks();
		//JourneyItems i = new JourneyItems();
		/*GameRegistry.addRecipe(new ItemStack(JourneyBlocks.greenGemBlock, 1), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.greenGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.purpleGemBlock, 1), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.purpleGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.blueGemBlock, 1), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.blueGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.yellowGemBlock, 1), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.yellowGem});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.greenGem, 9), new Object[] {"i", 'i', JourneyBlocks.greenGemBlock});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.purpleGem, 9), new Object[] {"i", 'i', JourneyBlocks.purpleGemBlock});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blueGem, 9), new Object[] {"i", 'i', JourneyBlocks.blueGemBlock});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.yellowGem, 9), new Object[] {"i", 'i', JourneyBlocks.yellowGemBlock});
		
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.igniter, 1), new Object[] {"iii", "jkj", "cfc", 'i', Blocks.netherrack, 'j', Items.iron_ingot, 'k', JourneyBlocks.lavaRock, 'c', Blocks.cobblestone, 'f', Items.flint_and_steel});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.eucaPortalFrame, 10), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.eucaPortalGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.depthsPortalFrame, 10), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.depthsPortalGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.cloudiaPortalFrame, 10), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.cloudiaPortalGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.corbaPortalFrame, 12), new Object[] {"iii", "iii", 'i', JourneyItems.corbaPortalGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.terraniaPortalFrame, 10), new Object[] {"iii", "iii", "iii", 'i', JourneyItems.terraniaPortalGem});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.frozenPortalFrame, 10), new Object[] {"iii", "idi", "iii", 'i', Items.snowball, 'd', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.summoningTable, 1), new Object[] {"dsd", "iii", "iii", 'i', JourneyItems.shadiumIngot, 'd', Items.diamond, 's', JourneyItems.sapphire});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.netherChest, 4), new Object[] {"ddd", "did", "ddd", 'i', Blocks.chest, 'd', JourneyItems.blood});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.eucaChest, 4), new Object[] {"ddd", "did", "ddd", 'i', Blocks.chest, 'd', JourneyBlocks.goldEucaPlank});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.frozenChest, 4), new Object[] {"ddd", "did", "ddd", 'i', Blocks.chest, 'd', JourneyBlocks.frozenPlanks});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.boilingChest, 4), new Object[] {"ddd", "did", "ddd", 'i', Blocks.chest, 'd', JourneyBlocks.boilingLog});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.boilingBars, 4), new Object[] {"ddd", "ddd", 'd', Items.blaze_rod});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.stoneCraftingTable, 1), new Object[] {"dd", "dd", 'd', Blocks.cobblestone});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.trophy, 1), new Object[] {"d d", "ddd", " d ", 'd', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.grindstone), new Object[] {" o ", "bib", "bbb", 'b', Blocks.brick_block, 'o', Blocks.obsidian, 'i', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(Blocks.web), new Object[] {"sss", "sss","sss", 's', Items.string});
		GameRegistry.addRecipe(new ItemStack(JourneyBlocks.netherFurnace, 1), new Object[] {"ddd", "d d", "ddd", 'd', Blocks.netherrack});*/
		
		//addWood(JourneyBlocks.eucaGoldLog, JourneyBlocks.goldEucaPlank, JourneyBlocks.eucaGoldStairs, 0, true);
		//addWood(JourneyBlocks.depthsLog, JourneyBlocks.depthsPlank, JourneyBlocks.depthsStairs, 1, true);
	}
	
	public static void addBlock(Block made, Item used) {
		//GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addBlock(Block made, Block used) {
		//GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	private static void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		/*GameRegistry.addShapelessRecipe(new ItemStack(plank, 4), new Object[] {log});
		GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] {"i  ", "ii ", "iii", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[] {"i", "i", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table), new Object[] {"ii", "ii", 'i', plank});
		//GameRegistry.addRecipe(new ItemStack(EssenceBlocks.halfSlab, 6, slabMeta), new Object[] {"iii", 'i', plank});
		if(smelt) GameRegistry.addSmelting(log, new ItemStack(Items.coal), 0.5F);*/
	}
}