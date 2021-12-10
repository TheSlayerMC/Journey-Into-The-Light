package net.jitl.common.entity.base.trades;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;

import java.util.Random;

public class CurrencyForItemsTrade implements VillagerTrades.ITrade {
    private final Item item;
    private final Item currency;
    private final int cost;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public CurrencyForItemsTrade(IItemProvider currency, IItemProvider p_i50539_1_, int p_i50539_2_, int p_i50539_3_, int p_i50539_4_) {
        this.item = p_i50539_1_.asItem();
        this.currency = currency.asItem();
        this.cost = p_i50539_2_;
        this.maxUses = p_i50539_3_;
        this.villagerXp = p_i50539_4_;
        this.priceMultiplier = 0.05F;
    }

    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
        ItemStack lvt_3_1_ = new ItemStack(this.item, this.cost);
        return new MerchantOffer(lvt_3_1_, new ItemStack(this.currency), this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}