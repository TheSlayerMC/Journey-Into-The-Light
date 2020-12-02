package net.jitl.datagen;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.jitl.util.EnumRecipePrefix;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public class JRecipeRegister {

	public static final JRecipeRegister INSTANCE = new JRecipeRegister();

	public JRecipeRegister() {
	}

	public void init(Consumer<IFinishedRecipe> recipeConsumer) {
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
	}

	public void buildItemRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		add3x3Recipe(recipeConsumer, JItems.FIRESTONE_SHARD, JItems.FIRESTONE_CLUMP);
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
	}

	private void add3x3Recipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider input, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', input)
				.pattern("###")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
	}

	private void addToolsetAndArmorRecipes(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, EnumRecipePrefix recipePrefix) {
		addToolsetRecipes(recipeConsumer, stickItem, materialItem, recipePrefix);
		addArmorRecipes(recipeConsumer, materialItem, recipePrefix);
	}

	private void addToolsetRecipes(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, EnumRecipePrefix recipePrefix) {
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

	private void addPickaxeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("###")
				.pattern(" I ")
				.pattern(" I ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addShovelRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("I")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addAxeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern("#I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addHoeRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("##")
				.pattern(" I")
				.pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addSwordRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stickItem, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem).define('I', stickItem)
				.pattern("#")
				.pattern("#")
				.pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addHelmetRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addChestplateRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("###")
				.pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addLeggingsRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("###")
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addBootsRecipe(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider materialItem, IItemProvider output) {
		ShapedRecipeBuilder.shaped(output, 1).define('#', materialItem)
				.pattern("# #")
				.pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
	}

	private void addSmeltingRecipe(IItemProvider input, IItemProvider output, float xpGiven, int time) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), output, xpGiven, time);
	}

	private void addBlastingRecipe(IItemProvider input, IItemProvider output, float xpGiven, int time) {
		CookingRecipeBuilder.blasting(Ingredient.of(input), output, xpGiven, time);
	}

	private void addCookingRecipe(IItemProvider input, IItemProvider output, float xpGiven, int time, CookingRecipeSerializer<?> serializer) {
		CookingRecipeBuilder.cooking(Ingredient.of(input), output, xpGiven, time, serializer);
	}

	/**
	 * Simplifies requirement keys by taking the registry name of the main ingredient and converting it into a key
	 *
	 * @param input The item or block being transformed into a key string
	 */
	private String inputToKey(IItemProvider input) {
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

	/**
	 * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item.
	 */
	protected static InventoryChangeTrigger.Instance has(IItemProvider itemIn) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(itemIn).build());
	}

	/**
	 * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item.
	 */
	protected static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... predicate) {
		return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY, MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, predicate);
	}
}
