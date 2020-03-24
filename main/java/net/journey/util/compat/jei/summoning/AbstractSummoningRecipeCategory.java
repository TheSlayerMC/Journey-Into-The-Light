package net.journey.util.compat.jei.summoning;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public abstract class AbstractSummoningRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/summoning.png");
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int input3 = 2;
	
	protected static final int output = 3;
	
	protected static final int input4 = 4;
	protected static final int input5 = 5;
	protected static final int input6 = 6;
	
	public AbstractSummoningRecipeCategory(IGuiHelper helper) {}
}