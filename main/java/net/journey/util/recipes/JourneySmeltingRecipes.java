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

public class JourneySmeltingRecipes {

	private static final JourneySmeltingRecipes SMELTING = new JourneySmeltingRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> dualSmeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static JourneySmeltingRecipes instance() {
		return SMELTING;
	}

	public static void init() {
		initSmeltingCrafting();
	}
	
	public static void initSmeltingCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();

		GameRegistry.addSmelting(JourneyItems.spawnerClump, new ItemStack(JourneyItems.spawnerBar), 1.0F);
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
		GameRegistry.addSmelting(JourneyItems.flamingGhastTentacle, new ItemStack(JourneyItems.friedFlamingGhastTentacale), 0.5F);
		
	}
	
	private JourneySmeltingRecipes() {
		this.addDualSmelting(JourneyItems.hellstoneMultiTool, JourneyItems.blazingFireball, new ItemStack(JourneyItems.multiToolOfEternalSmelting), 10.0F);
	}
	
	public void addDualSmeltingRecipeForBlock(Block input1, Block input2, ItemStack result, float experience) {
		this.addDualSmelting(Item.getItemFromBlock(input1), Item.getItemFromBlock(input2), result, experience);
	}
	
	public void addDualSmeltingRecipeForBlock(Item input1, Block input2, ItemStack result, float experience) {
		this.addDualSmelting(input1, Item.getItemFromBlock(input2), result, experience);
	}
	
	public void addDualSmeltingRecipeForBlock(Block input1, Item input2, ItemStack result, float experience) {
		this.addDualSmelting(Item.getItemFromBlock(input1), input2, result, experience);
	}
	
	public void addDualSmelting(Item input1, Item input2, ItemStack result, float experience) {
		this.addDualSmeltingRecipe(new ItemStack(input1, 1, 32767), new ItemStack(input2, 1, 32767), result, experience);
	}
	
	public void addDualSmelting(ItemStack input1, Item input2, ItemStack result, float experience) {
		this.addDualSmeltingRecipe(input1, new ItemStack(input2, 1, 32767), result, experience);
	}
	
	public void addDualSmelting(Item input1, ItemStack input2, ItemStack result, float experience) {
		this.addDualSmeltingRecipe(new ItemStack(input1, 1, 32767), input2, result, experience);
	}
	
	public void addDualSmeltingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if(getDualSmeltingResult(input1, input2) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.info("Ignored dual smelting recipe with conflicting input: " + input1 + " and " + input2 + " = " + result); return; }
		this.dualSmeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getDualSmeltingResult(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.dualSmeltingList.columnMap().entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
		return this.dualSmeltingList;
	}
	
	public float getDualSmeltingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		/*GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"b", "b", "s", 'b', block, 's', Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(multiTool), new Object[] {pick, shovel, hoe, axe});
		GameRegistry.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] {block});
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);*/
	}
}