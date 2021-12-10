package net.jitl.common.entity.base.trades;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

import java.util.Random;

public class ItemsForCurrencyTrade implements VillagerTrades.ITrade {
    private final ItemStack itemStack;
    private final Item currency;
    private final int emeraldCost;
    private final int numberOfItems;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public ItemsForCurrencyTrade(Block p_i50528_1_, Item currency, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
        this(new ItemStack(p_i50528_1_), currency, p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
    }

    public ItemsForCurrencyTrade(Item p_i50529_1_, Item currency, int p_i50529_2_, int p_i50529_3_, int p_i50529_4_) {
        this(new ItemStack(p_i50529_1_), currency, p_i50529_2_, p_i50529_3_, 12, p_i50529_4_);
    }

    public ItemsForCurrencyTrade(Item p_i50530_1_, Item currency, int p_i50530_2_, int p_i50530_3_, int p_i50530_4_, int p_i50530_5_) {
        this(new ItemStack(p_i50530_1_), currency, p_i50530_2_, p_i50530_3_, p_i50530_4_, p_i50530_5_);
    }

    public ItemsForCurrencyTrade(ItemStack p_i50531_1_, Item currency, int p_i50531_2_, int p_i50531_3_, int p_i50531_4_, int p_i50531_5_) {
        this(p_i50531_1_, currency, p_i50531_2_, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0.05F);
    }

    public ItemsForCurrencyTrade(ItemStack p_i50532_1_, Item currency, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_, int p_i50532_5_, float p_i50532_6_) {
        this.currency = currency;
        this.itemStack = p_i50532_1_;
        this.emeraldCost = p_i50532_2_;
        this.numberOfItems = p_i50532_3_;
        this.maxUses = p_i50532_4_;
        this.villagerXp = p_i50532_5_;
        this.priceMultiplier = p_i50532_6_;
    }

    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
        return new MerchantOffer(new ItemStack(currency, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}