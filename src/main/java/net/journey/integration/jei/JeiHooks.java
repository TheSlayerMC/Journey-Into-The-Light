package net.journey.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.integration.jei.summoning.SummoningRecipeCategory;
import net.journey.integration.jei.summoning.SummoningRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

@JEIPlugin
public class JeiHooks implements IModPlugin {

    static {
        JEIInteractions.setJEIActive();
    }

    @Override
    public void register(IModRegistry registry) {
        registerHiddenItems(registry);
        registry.addRecipeCatalyst(new ItemStack(JourneyBlocks.summoningTable), "journey.summoningtable");
        registry.addRecipes(compileSummoningRecipes(), "journey.summoningtable");
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientEyeOfOpening), ItemStack.class, "jei.jitl.ancient_eye.desc");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new SummoningRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    private ArrayList<SummoningRecipeWrapper> compileSummoningRecipes() {
        ArrayList<SummoningRecipeWrapper> summonRecipes = new ArrayList<SummoningRecipeWrapper>(6);

        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.blazierOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.soulWatcherOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.loggerOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.sentryKingOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.scaleOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.thunderbirdOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.corallatorOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.eudorOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.netherBeastOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.witheringBeastOrb));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.enchantedTerrastar));
        summonRecipes.add(new SummoningRecipeWrapper(JourneyItems.calciaOrb));

        return summonRecipes;
    }

    private void registerHiddenItems(IModRegistry registry) {
    }
}
