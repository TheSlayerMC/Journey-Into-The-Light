package net.journey.util.compat.jei.summoning;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.journey.util.compat.jei.RecipeCatagories;
import net.minecraft.client.Minecraft;
import net.slayer.api.SlayerAPI;

public class SummoningRecipeCategory extends AbstractSummoningRecipeCategory <SummoningRecipe>{

	private final IDrawable background;
	private final String name;
	
	public SummoningRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
		name = "Summoning Table";
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public String getModName() {
		return SlayerAPI.MOD_NAME;
	}
	
	@Override
	public String getUid() {
		return RecipeCatagories.SUMMONING;
	}
	
	public void setRecipe(IRecipeLayout layout, SummoningRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = layout.getItemStacks();
		stacks.init(input1, true, 44, 17);
		stacks.init(input2, true, 44, 35);
		stacks.init(input3, true, 44, 53);
		
		stacks.init(output, false, 80, 35);
		
		stacks.init(input4, true, 117, 17);
		stacks.init(input5, true, 117, 35);
		stacks.init(input6, true, 117, 53);
		stacks.set(ingredients);
	}
}
