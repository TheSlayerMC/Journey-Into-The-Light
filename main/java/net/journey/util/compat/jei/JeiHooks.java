package net.journey.util.compat.jei;
import java.util.ArrayList;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.util.compat.jei.summoning.SummoningRecipeCategory;
import net.journey.util.compat.jei.summoning.SummoningRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class JeiHooks implements IModPlugin {

	static {
		JEIInteractions.setJEIActive();
	}
	
	@Override
	public void register(IModRegistry registry) {
		registerHiddenItems(registry);	
		registry.addRecipeCatalyst(new ItemStack(JourneyBlocks.summoningTable), "journey.summoningtable");
		registry.addRecipes(compileSummoningRecipes(), "journey.summoningtable");
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new SummoningRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}
	
	private ArrayList<SummoningRecipeWrapper> compileSummoningRecipes() {
		ArrayList<SummoningRecipeWrapper> summonRecipes = new ArrayList<SummoningRecipeWrapper>(6);

		summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.blazierOrb));

		return summonRecipes;
	}

	private void registerHiddenItems(IModRegistry registry) {	}
}
