package net.jitl.datagen;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class JRecipeRegister extends JRecipeProvider {

	public JRecipeRegister(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		buildBlockRecipes(recipeConsumer);
		buildItemRecipes(recipeConsumer);
		buildToolRecipes(recipeConsumer);
		buildBlastingRecipes();
		buildSmeltingRecipes();
		buildCookingRecipes();
		buildSmithingRecipes(recipeConsumer);
	}

	public void buildBlockRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		addOreBlockRecipe(recipeConsumer, JItems.SAPPHIRE, JBlocks.SAPPHIRE_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.LUNIUM_INGOT, JBlocks.LUNIUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.SHADIUM_INGOT, JBlocks.SHADIUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.IRIDIUM_NUGGET, JBlocks.IRIDIUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.BLOODCRUST_INGOT, JBlocks.BLOODCRUST_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.ENDERILLIUM_SHARD, JBlocks.ENDERILLIUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.ASH, JBlocks.ASHUAL_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.CELESTIUM_INGOT, JBlocks.CELESTIUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.MEKYUM_INGOT, JBlocks.MEKYUM_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.STORON_INGOT, JBlocks.STORON_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.DREADIRON_INGOT, JBlocks.DREADIRON_BLOCK);
		addOreBlockRecipe(recipeConsumer, JItems.PERIDOT_GEMSTONE, JBlocks.PERIDOT_BLOCK);

		add2x2Recipe(recipeConsumer, JItems.MUD_BALL, JBlocks.BLOCK_OF_MUD);
	}

	public void buildItemRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		add3x3Recipe(recipeConsumer, JItems.FIRESTONE_SHARD, JItems.FIRESTONE_CLUMP);

		ShapelessRecipeBuilder.shapeless(JItems.MUD_BALL, 4)
				.requires(JBlocks.BLOCK_OF_MUD)
				.unlockedBy(inputToKey(JBlocks.BLOCK_OF_MUD),
						has(JBlocks.BLOCK_OF_MUD))
				.save(recipeConsumer);

		ShapelessRecipeBuilder.shapeless(JItems.LUNIUM_INGOT, 1)
				.requires(JItems.LUNIUM_POWDER, 8)
				.requires(Items.IRON_INGOT, 1)
				.unlockedBy(inputToKey(JItems.LUNIUM_POWDER),
						has(JItems.LUNIUM_POWDER))
				.group("lunium_ingot")
				.save(recipeConsumer);

		ShapedRecipeBuilder.shaped(JItems.POWDER_OF_ESSENCIA, 8).define('#', JItems.LUNIUM_POWDER).define('I', JItems.BLOOD)
				.pattern("###")
				.pattern("#I#")
				.pattern("###").unlockedBy(inputToKey(JItems.BLOOD), has(JItems.BLOOD)).save(recipeConsumer);

	}

	public void buildToolRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.SAPPHIRE, RecipePrefix.SAPPHIRE);
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.LUNIUM_INGOT, RecipePrefix.LUNIUM);
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.SHADIUM_INGOT, RecipePrefix.SHADIUM);
	}

	public void buildBlastingRecipes() {
		addBlastingRecipe(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_CLUMP, 1.0F, 100);
		addBlastingRecipe(JBlocks.LUNIUM_ORE, JItems.LUNIUM_INGOT, 1.0F, 100);
		addBlastingRecipe(JItems.LUNIUM_POWDER, JItems.LUNIUM_INGOT, 1.0F, 100);
	}

	public void buildSmeltingRecipes() {
		addSmeltingRecipe(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_CLUMP, 1.0F, 200);
		addSmeltingRecipe(JItems.LUNIUM_POWDER, JItems.LUNIUM_INGOT, 1.0F, 200);
	}

	public void buildCookingRecipes() {
//		addCookingRecipe();
	}

	public void buildSmithingRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_HELMET, JItems.LUNIUM_INGOT, JItems.LUNIUM_HELMET);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_CHESTPLATE, JItems.LUNIUM_INGOT, JItems.LUNIUM_CHESTPLATE);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_LEGGINGS, JItems.LUNIUM_INGOT, JItems.LUNIUM_LEGGINGS);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_BOOTS, JItems.LUNIUM_INGOT, JItems.LUNIUM_BOOTS);

		addSmithingRecipe(recipeConsumer, Items.DIAMOND_SWORD, JItems.LUNIUM_INGOT, JItems.LUNIUM_SWORD);

		addSmithingRecipe(recipeConsumer, Items.DIAMOND_AXE, JItems.LUNIUM_INGOT, JItems.LUNIUM_AXE);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_PICKAXE, JItems.LUNIUM_INGOT, JItems.LUNIUM_PICKAXE);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_SHOVEL, JItems.LUNIUM_INGOT, JItems.LUNIUM_SHOVEL);
		addSmithingRecipe(recipeConsumer, Items.DIAMOND_HOE, JItems.LUNIUM_INGOT, JItems.LUNIUM_HOE);

		addSmithingRecipe(recipeConsumer, Items.IRON_HELMET, JItems.DREADIRON_INGOT, JItems.DREADIRON_HELMET);
		addSmithingRecipe(recipeConsumer, Items.IRON_CHESTPLATE, JItems.DREADIRON_INGOT, JItems.DREADIRON_CHESTPLATE);
		addSmithingRecipe(recipeConsumer, Items.IRON_LEGGINGS, JItems.DREADIRON_INGOT, JItems.DREADIRON_LEGGINGS);
		addSmithingRecipe(recipeConsumer, Items.IRON_BOOTS, JItems.DREADIRON_INGOT, JItems.DREADIRON_BOOTS);
	}
}
