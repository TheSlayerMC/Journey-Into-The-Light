package net.journey.util.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class JourneySmeltingRecipes {

	public static void init() {
		initSmeltingCrafting();
	}

	public static void initSmeltingCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();

		GameRegistry.addSmelting(i.spawnerClump, new ItemStack(i.spawnerBar), 1.0F);
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(b.smoothGlass, 1), 1.0F);
		GameRegistry.addSmelting(i.flamingBeef, new ItemStack(JourneyItems.flamingBeefCooked), 0.5F);
		GameRegistry.addSmelting(i.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(i.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(i.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
		GameRegistry.addSmelting(i.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 0.5F);
		GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 0.5F);
		GameRegistry.addSmelting(Items.EGG, new ItemStack(i.friedEgg), 0.5F);
		GameRegistry.addSmelting(i.rocMeat, new ItemStack(i.cookedRocMeat), 0.5F);
		GameRegistry.addSmelting(i.ghastTentacle, new ItemStack(i.friedGhastTentacale), 0.5F);
		GameRegistry.addSmelting(i.flamingGhastTentacle, new ItemStack(i.friedFlamingGhastTentacale), 0.5F);
	
	}
	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}
}