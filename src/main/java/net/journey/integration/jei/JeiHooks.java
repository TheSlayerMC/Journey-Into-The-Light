package net.journey.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.integration.jei.summoning.SummoningRecipeCategory;
import net.journey.integration.jei.summoning.SummoningRecipeWrapper;
import net.minecraft.client.resources.I18n;
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
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientEyeOfOpening), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_eye.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece1), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_fragment.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece2), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_fragment.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece3), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_fragment.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece4), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_fragment.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.ANCIENT_OBELISK), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_obelisk.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.ANCIENT_SOCKET), VanillaTypes.ITEM, I18n.format("jei.jitl.ancient_socket.desc"));

        registry.addIngredientInfo(new ItemStack(JourneyBlocks.sapphireOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_sapphire.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.shadiumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_shadium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.luniumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_lunium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.iridiumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_iridium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.hellstoneOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_hellstone.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.bloodRock), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bloodrock.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.bleedstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bleedstone.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyBlocks.smithstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_smithstone.desc"));

        registry.addIngredientInfo(new ItemStack(JourneyItems.sapphire), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_sapphire.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.shadiumIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_shadium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.luniumIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_lunium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.iridium), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_iridium.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.hellstoneIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_hellstone.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.bleedstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bleedstone.desc"));
        registry.addIngredientInfo(new ItemStack(JourneyItems.smithstoneDust), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_smithstone.desc"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new SummoningRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    private ArrayList<SummoningRecipeWrapper> compileSummoningRecipes() {
        ArrayList<SummoningRecipeWrapper> summonRecipes = new ArrayList<>(12);

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
