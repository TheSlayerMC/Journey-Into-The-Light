package net.journey.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

public class JourneyMerchantRecipe extends MerchantRecipe {

    public JourneyMerchantRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell) {
        super(buy1, buy2, sell);
    }

    @Override
    public boolean isRecipeDisabled() {
        return false;
    }
}
