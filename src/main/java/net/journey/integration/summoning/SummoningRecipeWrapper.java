package net.journey.integration.summoning;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.journey.init.items.JourneyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SummoningRecipeWrapper implements IRecipeWrapper {

    private final Item output;
    ArrayList<ItemStack> inputs = new ArrayList<ItemStack>(6);

    public SummoningRecipeWrapper(Item output) {
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        this.setSummons(ingredients);
        ingredients.setInputs(VanillaTypes.ITEM, inputs);
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(output, 1));
    }

    public void setSummons(IIngredients ingredients) {
        if (output == JourneyItems.blazierOrb) {
            inputs.add(new ItemStack(JourneyItems.boilPowder));
            inputs.add(new ItemStack(JourneyItems.boilPowder));
            inputs.add(new ItemStack(JourneyItems.boilPowder));

            inputs.add(new ItemStack(JourneyItems.blazingFireball));

            inputs.add(new ItemStack(JourneyItems.boilPowder));
            inputs.add(new ItemStack(JourneyItems.boilPowder));
            inputs.add(new ItemStack(JourneyItems.boilPowder));
        } else if (output == JourneyItems.soulWatcherOrb) {
            inputs.add(new ItemStack(JourneyItems.snakeSkin));
            inputs.add(new ItemStack(JourneyItems.concentratedBlood));
            inputs.add(new ItemStack(JourneyItems.snakeSkin));

            inputs.add(new ItemStack(JourneyItems.sizzlingEye));

            inputs.add(new ItemStack(JourneyItems.snakeSkin));
            inputs.add(new ItemStack(JourneyItems.concentratedBlood));
            inputs.add(new ItemStack(JourneyItems.snakeSkin));
        } else if (output == JourneyItems.loggerOrb) {
            inputs.add(new ItemStack(JourneyItems.natureTablet));
            inputs.add(new ItemStack(JourneyItems.enchantedLeaf));
            inputs.add(new ItemStack(JourneyItems.natureTablet));

            inputs.add(new ItemStack(JourneyItems.overgrownNatureBall));

            inputs.add(new ItemStack(JourneyItems.natureTablet));
            inputs.add(new ItemStack(JourneyItems.enchantedLeaf));
            inputs.add(new ItemStack(JourneyItems.natureTablet));
        } else if (output == JourneyItems.sentryKingOrb) {
            inputs.add(new ItemStack(JourneyItems.overseeingEye));
            inputs.add(new ItemStack(JourneyItems.collectorRock));
            inputs.add(new ItemStack(JourneyItems.overseeingEye));

            inputs.add(new ItemStack(JourneyItems.overseeingTablet));

            inputs.add(new ItemStack(JourneyItems.overseeingEye));
            inputs.add(new ItemStack(JourneyItems.collectorRock));
            inputs.add(new ItemStack(JourneyItems.overseeingEye));
        } else if (output == JourneyItems.scaleOrb) {
            inputs.add(new ItemStack(JourneyItems.scale));
            inputs.add(new ItemStack(JourneyItems.beastlyStomach));
            inputs.add(new ItemStack(JourneyItems.scale));

            inputs.add(new ItemStack(JourneyItems.darkOrb));

            inputs.add(new ItemStack(JourneyItems.scale));
            inputs.add(new ItemStack(JourneyItems.beastlyStomach));
            inputs.add(new ItemStack(JourneyItems.scale));
        } else if (output == JourneyItems.thunderbirdOrb) {
            inputs.add(new ItemStack(JourneyItems.rocFeather));
            inputs.add(new ItemStack(JourneyItems.darkCrystal));
            inputs.add(new ItemStack(JourneyItems.rocFeather));

            inputs.add(new ItemStack(JourneyItems.darkOrb));

            inputs.add(new ItemStack(JourneyItems.rocFeather));
            inputs.add(new ItemStack(JourneyItems.darkCrystal));
            inputs.add(new ItemStack(JourneyItems.rocFeather));
        } else if (output == JourneyItems.corallatorOrb) {
            inputs.add(new ItemStack(JourneyItems.gateKeys));
            inputs.add(new ItemStack(JourneyItems.silverClump));
            inputs.add(new ItemStack(JourneyItems.gateKeys));

            inputs.add(new ItemStack(JourneyItems.metalDisk));

            inputs.add(new ItemStack(JourneyItems.gateKeys));
            inputs.add(new ItemStack(JourneyItems.silverClump));
            inputs.add(new ItemStack(JourneyItems.gateKeys));
        } else if (output == JourneyItems.eudorOrb) {
            inputs.add(new ItemStack(JourneyItems.gateKeys));
            inputs.add(new ItemStack(JourneyItems.goldClump));
            inputs.add(new ItemStack(JourneyItems.gateKeys));

            inputs.add(new ItemStack(JourneyItems.royalDisk));

            inputs.add(new ItemStack(JourneyItems.gateKeys));
            inputs.add(new ItemStack(JourneyItems.goldClump));
            inputs.add(new ItemStack(JourneyItems.gateKeys));
        } else if (output == JourneyItems.netherBeastOrb) {
            inputs.add(new ItemStack(JourneyItems.hellstoneIngot));
            inputs.add(new ItemStack(JourneyItems.hellShards));
            inputs.add(new ItemStack(JourneyItems.hellstoneIngot));

            inputs.add(new ItemStack(JourneyItems.hellcrustIngot));

            inputs.add(new ItemStack(JourneyItems.hellstoneIngot));
            inputs.add(new ItemStack(JourneyItems.hellShards));
            inputs.add(new ItemStack(JourneyItems.hellstoneIngot));
        } else if (output == JourneyItems.witheringBeastOrb) {
            inputs.add(new ItemStack(JourneyItems.withicSpine));
            inputs.add(new ItemStack(JourneyItems.lostSoul));
            inputs.add(new ItemStack(JourneyItems.withicSpine));

            inputs.add(new ItemStack(JourneyItems.withicSoul));

            inputs.add(new ItemStack(JourneyItems.withicSpine));
            inputs.add(new ItemStack(JourneyItems.lostSoul));
            inputs.add(new ItemStack(JourneyItems.withicSpine));
        } else if (output == JourneyItems.enchantedTerrastar) {
            inputs.add(new ItemStack(JourneyItems.earthenCrystal));
            inputs.add(new ItemStack(JourneyItems.purplePowder));
            inputs.add(new ItemStack(JourneyItems.earthenCrystal));

            inputs.add(new ItemStack(JourneyItems.terrastar));

            inputs.add(new ItemStack(JourneyItems.earthenCrystal));
            inputs.add(new ItemStack(JourneyItems.purplePowder));
            inputs.add(new ItemStack(JourneyItems.earthenCrystal));
        } else if (output == JourneyItems.calciaOrb) {
            inputs.add(new ItemStack(JourneyItems.spawnerBar));
            inputs.add(new ItemStack(JourneyItems.ash));
            inputs.add(new ItemStack(JourneyItems.spawnerBar));

            inputs.add(new ItemStack(JourneyItems.hellShards));

            inputs.add(new ItemStack(JourneyItems.spawnerBar));
            inputs.add(new ItemStack(JourneyItems.ash));
            inputs.add(new ItemStack(JourneyItems.spawnerBar));
        }
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }
}
