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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class JourneySmeltingRecipes {

	public static JourneySmeltingRecipes INSTANCE = new JourneySmeltingRecipes();
	
	public static JourneySmeltingRecipes instance() {
		return INSTANCE;
	}
	
	public static void init() {
		initSmeltingCrafting();
	}

	public static void initSmeltingCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();

		FurnaceRecipes.instance().addSmelting(i.spawnerClump, new ItemStack(i.spawnerBar), 1.0F);
		//FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(Blocks.GLASS), new ItemStack(b.smoothGlass, 1), 1.0F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.verditeOre), new ItemStack(i.verditeIngot, 1), 1.0F);
		FurnaceRecipes.instance().addSmelting(i.flamingBeef, new ItemStack(JourneyItems.flamingBeefCooked), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 0.5F);
		FurnaceRecipes.instance().addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 0.5F);
		FurnaceRecipes.instance().addSmelting(Items.EGG, new ItemStack(i.friedEgg), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.rocMeat, new ItemStack(i.cookedRocMeat), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.ghastTentacle, new ItemStack(i.friedGhastTentacale), 0.5F);
		FurnaceRecipes.instance().addSmelting(i.flamingGhastTentacle, new ItemStack(i.friedFlamingGhastTentacale), 0.5F);
	
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.shadiumOre), new ItemStack(i.shadiumIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.luniumOre), new ItemStack(i.luniumIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.hellstoneOre), new ItemStack(i.hellstoneIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.celestiumOre), new ItemStack(i.celestiumIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.mekyumOre), new ItemStack(i.mekyumIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.koriteOre), new ItemStack(i.koriteIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.storonOre), new ItemStack(i.storonIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.flairiumOre), new ItemStack(i.flairiumIngot), 0.5F);
		FurnaceRecipes.instance().addSmelting(SlayerAPI.toItem(b.orbaditeOre), new ItemStack(i.orbaditeIngot), 0.5F);

	}
	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}
}