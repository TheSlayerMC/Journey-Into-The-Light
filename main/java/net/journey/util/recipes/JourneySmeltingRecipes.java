package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JourneySmeltingRecipes {

	public static void init() {
		initSmeltingCrafting();
	}

	public static void initSmeltingCrafting() {
		//JourneyBlocks b = new JourneyBlocks();
		//JourneyItems i = new JourneyItems();

		/*GameRegistry.addSmelting(JourneyItems.spawnerClump, new ItemStack(JourneyItems.spawnerBar), 1.0F);
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(JourneyBlocks.smoothGlass), 1.0F);
		GameRegistry.addSmelting(JourneyItems.flamingBeef, new ItemStack(JourneyItems.flamingBeefCooked), 0.5F);
		GameRegistry.addSmelting(JourneyItems.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
		GameRegistry.addSmelting(JourneyItems.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items.LEATHER), new ItemStack(Items.ROTTEN_FLESH), 0.5F);
		GameRegistry.addSmelting(Items.EGG, new ItemStack(JourneyItems.friedEgg), 0.5F);
		GameRegistry.addSmelting(JourneyItems.rocMeat, new ItemStack(JourneyItems.cookedRocMeat), 0.5F);
		GameRegistry.addSmelting(JourneyItems.ghastTentacle, new ItemStack(JourneyItems.friedGhastTentacale), 0.5F);
		GameRegistry.addSmelting(JourneyItems.flamingGhastTentacle, new ItemStack(JourneyItems.friedFlamingGhastTentacale), 0.5F);*/
	
	}
	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		/*GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"b", "b", "s", 'b', block, 's', Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(multiTool), new Object[] {pick, shovel, hoe, axe});
		GameRegistry.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] {block});
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);*/
	}
}