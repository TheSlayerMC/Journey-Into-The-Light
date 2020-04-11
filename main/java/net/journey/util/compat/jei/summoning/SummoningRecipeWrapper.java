package net.journey.util.compat.jei.summoning;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.journey.JourneyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SummoningRecipeWrapper implements IRecipeWrapper {

	private final Item output;

	public SummoningRecipeWrapper(Item output) {
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>(6);
		if (output == JourneyItems.blazierOrb) {
			inputs.add(new ItemStack(JourneyItems.boilPowder));
			inputs.add(new ItemStack(JourneyItems.boilPowder));
			inputs.add(new ItemStack(JourneyItems.boilPowder));
			
			inputs.add(new ItemStack(JourneyItems.blazingFireball));
			
			inputs.add(new ItemStack(JourneyItems.boilPowder));
			inputs.add(new ItemStack(JourneyItems.boilPowder));
			inputs.add(new ItemStack(JourneyItems.boilPowder));
		}
		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(output, 1));
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}
}
