package net.journey.util.compat.jei.summoning;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class SummoningRecipeCategory implements IRecipeCategory<SummoningRecipeWrapper> {

    private static ResourceLocation textureResource;
    private final IDrawable background;
    private final IDrawable icon;

    public SummoningRecipeCategory(IGuiHelper guiHelper) {
        textureResource = new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/summoning_jei.png");
        background = guiHelper.createDrawable(textureResource, 0, 3, 176, 80);
        icon = guiHelper.createDrawableIngredient(new ItemStack(JourneyBlocks.summoningTable));
    }

    @Override
    public String getUid() {
        return "journey.summoningtable";
    }

    @Override
    public String getTitle() {
        return JourneyBlocks.summoningTable.getLocalizedName();
    }

    @Override
    public String getModName() {
        // TODO Auto-generated method stub
        return "Journey Into the Light";
    }

    @Override
    public IDrawable getBackground() {
        // TODO Auto-generated method stub
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SummoningRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();
        guiStacks.init(0, true, 20, 13);
        guiStacks.init(1, true, 20, 31);
        guiStacks.init(2, true, 20, 49);

        guiStacks.init(3, true, 56, 31);

        guiStacks.init(4, true, 93, 13);
        guiStacks.init(5, true, 93, 31);
        guiStacks.init(6, true, 93, 49);

        guiStacks.init(7, false, 138, 31);

        guiStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(1));
        guiStacks.set(2, ingredients.getInputs(VanillaTypes.ITEM).get(2));

        guiStacks.set(3, ingredients.getInputs(VanillaTypes.ITEM).get(3));

        guiStacks.set(4, ingredients.getInputs(VanillaTypes.ITEM).get(4));
        guiStacks.set(5, ingredients.getInputs(VanillaTypes.ITEM).get(5));
        guiStacks.set(6, ingredients.getInputs(VanillaTypes.ITEM).get(6));

        guiStacks.set(7, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }
}
