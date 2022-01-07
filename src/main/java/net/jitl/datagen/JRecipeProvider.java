package net.jitl.datagen;

import net.jitl.JITL;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public class JRecipeProvider extends RecipeProvider {

	public JRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	protected void addSmithingRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike input, ItemLike modifier, Item result) {
		UpgradeRecipeBuilder.smithing(Ingredient.of(input), Ingredient.of(modifier), result).unlocks("has_" + modifier.toString().toLowerCase(), has(modifier)).save(recipeConsumer, result.getRegistryName() + "_smithing");
		JITL.LOGGER.info(modifier.toString().toLowerCase());
	}

	protected void add3x3Recipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike input, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("###")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	protected void addOreBlockRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike input, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("###")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
		ShapelessRecipeBuilder.shapeless(input, 9).requires(output).unlockedBy(inputToKey(output), has(output)).group(input.asItem().toString()).save(recipeConsumer, input.asItem().getRegistryName() + "_from_block");

		JITL.LOGGER.info(input.asItem().getRegistryName());
	}

	protected void add2x2Recipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike input, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("##")
				.pattern("##").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	protected void addToolsetAndArmorRecipes(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, RecipePrefix recipePrefix) {
		addToolsetRecipes(recipeConsumer, stickItem, materialItem, recipePrefix);
		addArmorRecipes(recipeConsumer, materialItem, recipePrefix);
	}

	protected void addToolsetRecipes(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, RecipePrefix recipePrefix) {
		addAxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName((recipePrefix.getString() + "axe")));
		addPickaxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "pickaxe"));
		addShovelRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "shovel"));
		addSwordRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "sword"));
		addHoeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "hoe"));
	}

	public void addArmorRecipes(Consumer<FinishedRecipe> recipeConsumer, ItemLike materialItem, RecipePrefix recipePrefix) {
		addHelmetRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "helmet")));
		addChestplateRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "chestplate")));
		addLeggingsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "leggings")));
		addBootsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "boots")));
	}

	protected void addPickaxeRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("###")
				.pattern(" I ")
				.pattern(" I ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addShovelRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("I")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addAxeRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern("#I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addHoeRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern(" I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addSwordRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("#")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addHelmetRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addChestplateRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addLeggingsRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addBootsRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike materialItem, ItemLike output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	protected void addSmeltingRecipe(ItemLike input, ItemLike output, float xpGiven, int time) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), output, xpGiven, time);
	}

	protected void addBlastingRecipe(ItemLike input, ItemLike output, float xpGiven, int time) {
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), output, xpGiven, time);
	}

	protected void addCookingRecipe(ItemLike input, ItemLike output, float xpGiven) {
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpGiven, 100, RecipeSerializer.SMOKING_RECIPE);
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpGiven, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
	}

	/**
	 * Simplifies requirement keys by taking the registry name of the main ingredient and converting it into a key
	 *
	 * @param input The item or block being transformed into a key string
	 */
	protected String inputToKey(ItemLike input) {
		String key = "has_" + Objects.requireNonNull(input.asItem().getRegistryName()).getPath();
		System.out.println(key);
		return key;
	}

	/**
	 * Helps simplify recipe creation.
	 * Allows the ingredient to be referenced with a prefix and suffix, instead of having to be referenced directly in the method or constructor.
	 * This is useful in the case of bulk registering, as seen in the {@link #addArmorRecipes(Consumer, ItemLike, RecipePrefix)} (Consumer, IItemProvider, RecipePrefix)} method
	 *
	 * @param registryName
	 * @return
	 */
	public ItemLike getItemFromRegistryName(String registryName) {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, registryName));
	}

	public enum RecipePrefix {

		SAPPHIRE("sapphire_"),
		LUNIUM("lunium_"),
		SHADIUM("shadium_");

		String prefix;

		RecipePrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getString() {
			return prefix;
		}
	}
}
