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
    private final int count;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public CurrencyForItemsTrade(IItemProvider currency, int cost, IItemProvider trade, int count, int maxUses, int villagerXp) {
        this.item = trade.asItem();
        this.currency = currency.asItem();
        this.cost = cost;
        this.count = count;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
        this.priceMultiplier = 0.05F;
    }

    public MerchantOffer getOffer(Entity entity, Random random) {
        ItemStack currencyStack = new ItemStack(this.currency, this.cost);
        ItemStack tradeStack = new ItemStack(this.item, this.count);

        return new MerchantOffer(currencyStack, tradeStack, this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}