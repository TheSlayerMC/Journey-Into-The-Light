package net.journey.enums;

import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum EnumSummoningRecipes {
	BLAZIER(is(JourneyItems.boilPowder),
			is(JourneyItems.boilPowder),
			is(JourneyItems.boilPowder),

			is(JourneyItems.blazingFireball),

			is(JourneyItems.boilPowder),
			is(JourneyItems.boilPowder),
			is(JourneyItems.boilPowder));

	private final ItemStack[] ingredients;

	EnumSummoningRecipes(ItemStack... ingredients) {
		this.ingredients = ingredients;
	}

	public ItemStack[] getIngredients() {
		return this.ingredients;
	}

	public ItemStack getTopLeftIngredient() {
		return this.ingredients[0];
	}

	public ItemStack getMiddleLeftIngredient() {
		return this.ingredients[1];
	}

	public ItemStack getBottomLeftIngredient() {
		return this.ingredients[2];
	}

	public ItemStack getMiddleIngredient() {
		return this.ingredients[3];
	}

	public ItemStack getTopRightIngredient() {
		return this.ingredients[4];
	}

	public ItemStack getMiddleRightIngredient() {
		return this.ingredients[5];
	}

	public ItemStack getBottomRightIngredient() {
		return this.ingredients[6];
	}

	private static ItemStack is(Item item) {
		return new ItemStack(item);
	}

	private static ItemStack is(Block block) {
		return new ItemStack(block);
	}
}
