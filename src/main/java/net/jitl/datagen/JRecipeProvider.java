package net.jitl.datagen;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.Objects;
import java.util.function.Consumer;

public class JRecipeProvider extends ForgeRecipeProvider {

	public JRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
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
	}

	private void addOreBlockRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider input, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input).pattern("###").pattern("###").pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	private String inputToKey(IItemProvider input) {
		String key = "has_" + Objects.requireNonNull(input.asItem().getRegistryName()).toString().replace(JITL.MODID + ":", "");
		System.out.println(key);
		return key;
	}
}
