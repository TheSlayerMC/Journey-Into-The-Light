package net.jitl.datagen;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.jitl.util.EnumRecipePrefix;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;

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
	}

	public void buildBlockRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		add3x3Recipe(recipeConsumer, JItems.SAPPHIRE, JBlocks.SAPPHIRE_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.LUNIUM_INGOT, JBlocks.LUNIUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.SHADIUM_INGOT, JBlocks.SHADIUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.IRIDIUM_NUGGET, JBlocks.IRIDIUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.BLOODCRUST_INGOT, JBlocks.BLOODCRUST_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.ENDERILLIUM_SHARD, JBlocks.ENDERILLIUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.ASH, JBlocks.ASHUAL_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.CELESTIUM_INGOT, JBlocks.CELESTIUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.MEKYUM_INGOT, JBlocks.MEKYUM_BLOCK);
		add3x3Recipe(recipeConsumer, JItems.STORON_INGOT, JBlocks.STORON_BLOCK);

		add2x2Recipe(recipeConsumer, JItems.MUD_BALL, JBlocks.BLOCK_OF_MUD);
	}

	public void buildItemRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		add3x3Recipe(recipeConsumer, JItems.FIRESTONE_SHARD, JItems.FIRESTONE_CLUMP);
		ShapelessRecipeBuilder.shapeless(JItems.MUD_BALL, 4).requires(JBlocks.BLOCK_OF_MUD).unlockedBy(inputToKey(JBlocks.BLOCK_OF_MUD), has(JBlocks.BLOCK_OF_MUD)).save(recipeConsumer);
	}

	public void buildToolRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.SAPPHIRE, EnumRecipePrefix.SAPPHIRE);
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.LUNIUM_INGOT, EnumRecipePrefix.LUNIUM);
		addToolsetAndArmorRecipes(recipeConsumer, JItems.OBSIDIAN_ROD, JItems.SHADIUM_INGOT, EnumRecipePrefix.SHADIUM);
	}

	public void buildBlastingRecipes() {
		addBlastingRecipe(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_CLUMP, 1.0F, 100);
	}

	public void buildSmeltingRecipes() {
		addSmeltingRecipe(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_CLUMP, 1.0F, 200);
	}

	public void buildCookingRecipes() {
//		addCookingRecipe();
	}
}
