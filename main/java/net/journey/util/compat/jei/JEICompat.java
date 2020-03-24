package net.journey.util.compat.jei;

import java.util.IllegalFormatException;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.journey.JourneyBlocks;
import net.journey.util.compat.jei.summoning.SummoningRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin {
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration reg) {
		final IJeiHelpers helpers = reg.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		reg.addRecipeCategories(new SummoningRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry reg) {
		reg.addRecipeCatalyst(new ItemStack(JourneyBlocks.summoningTable), RecipeCatagories.SUMMONING);
	}
	
	public static String translateToLocal(String key) {
		if(I18n.canTranslate(key))
			return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String string = translateToLocal(key);
		try {
			return String.format(string, format);
		} catch(IllegalFormatException exception) {
			return "Format error: " + string;
		}
	}
}
