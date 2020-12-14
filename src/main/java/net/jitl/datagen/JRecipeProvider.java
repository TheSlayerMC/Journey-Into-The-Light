package net.jitl.datagen;

import net.jitl.JITL;
import net.jitl.util.EnumRecipePrefix;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public class JRecipeProvider extends ForgeRecipeProvider {

	public JRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	protected void add3x3Recipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider input, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("###")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	protected void add2x2Recipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider input, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("##")
				.pattern("##").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	protected void addToolsetAndArmorRecipes(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, EnumRecipePrefix recipePrefix) {
		addToolsetRecipes(recipeConsumer, stickItem, materialItem, recipePrefix);
		addArmorRecipes(recipeConsumer, materialItem, recipePrefix);
	}

	protected void addToolsetRecipes(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, EnumRecipePrefix recipePrefix) {
		addAxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName((recipePrefix.getString() + "axe")));
		addPickaxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "pickaxe"));
		addShovelRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "shovel"));
		addSwordRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "sword"));
		addHoeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "hoe"));
	}

	public void addArmorRecipes(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, EnumRecipePrefix recipePrefix) {
		addHelmetRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "helmet")));
		addChestplateRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "chestplate")));
		addLeggingsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "leggings")));
		addBootsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "boots")));
	}

	protected void addPickaxeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("###")
				.pattern(" I ")
				.pattern(" I ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addShovelRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("I")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addAxeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern("#I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addHoeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern(" I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addSwordRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("#")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addHelmetRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addChestplateRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addLeggingsRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addBootsRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addSmeltingRecipe(IItemProvider input, IItemProvider output, float xpGiven, int time) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), output, xpGiven, time);
	}

	protected void addBlastingRecipe(IItemProvider input, IItemProvider output, float xpGiven, int time) {
		CookingRecipeBuilder.blasting(Ingredient.of(input), output, xpGiven, time);
	}

	protected void addCookingRecipe(IItemProvider input, IItemProvider output, float xpGiven) {
		CookingRecipeBuilder.cooking(Ingredient.of(input), output, xpGiven, 100, IRecipeSerializer.SMOKING_RECIPE);
		CookingRecipeBuilder.cooking(Ingredient.of(input), output, xpGiven, 600, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE);
	}

	/**
	 * Simplifies requirement keys by taking the registry name of the main ingredient and converting it into a key
	 *
	 * @param input The item or block being transformed into a key string
	 */
	protected String inputToKey(IItemProvider input) {
		String key = "has_" + Objects.requireNonNull(input.asItem().getRegistryName()).getPath();
		System.out.println(key);
		return key;
	}

	/**
	 * Helps simplify recipe creation.
	 * Allows the ingredient to be referenced with a prefix and suffix, instead of having to be referenced directly in the method or constructor.
	 * This is useful in the case of bulk registering, as seen in the {@link #addArmorRecipes(Consumer, IItemProvider, EnumRecipePrefix)} method
	 *
	 * @param registryName
	 * @return
	 */
	public IItemProvider getItemFromRegistryName(String registryName) {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, registryName));
	}
}
