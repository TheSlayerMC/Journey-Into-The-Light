package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.init.JItems;
import net.jitl.init.JStructures;
import net.jitl.init.JVillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class VillagerTradesEventHandler {

    @SubscribeEvent()
    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CARTOGRAPHER) {
            List<VillagerTrades.ITrade> level5_trades = event.getTrades().get(5);
            level5_trades.add(new MapTrade(Items.EMERALD, 15, Items.COMPASS, 1, JStructures.ILlAGER_BUNKER.getStructure(), MapDecoration.Type.BANNER_LIGHT_GRAY, 12, 5));
            //trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(JItems.SAPPHIRE, 1), 1, 1, 0));
        }
        if (event.getType() == JVillagerRegistry.ESKIMO_PROFESSION.get()) {
            List<VillagerTrades.ITrade> level5_trades = event.getTrades().get(1);
            level5_trades.add(new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE, Items.COMPASS, 1, 12, 5));
        }
    }

    public static class MapTrade implements VillagerTrades.ITrade {
        private final Item currency;
        private final int count;
        private final Item currency2;
        private final int count2;
        private final Structure<?> destination;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public MapTrade(Item currency, int count, Item currency2, int count2, Structure<?> structureName, MapDecoration.Type mapDecorationType, int maxUses, int xpValue) {
            this.currency = currency;
            this.count = count;
            this.currency2 = currency2;
            this.count2 = count2;
            this.destination = structureName;
            this.destinationType = mapDecorationType;
            this.maxUses = maxUses;
            this.villagerXp = xpValue;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random rand) {
            if (!(trader.level instanceof ServerWorld)) {
                return null;
            } else {
                ServerWorld serverworld = (ServerWorld) trader.level;
                BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, trader.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = FilledMapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
                    FilledMapItem.renderBiomePreviewMap(serverworld, itemstack);
                    MapData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(new TranslationTextComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
                    if (currency2 != null && count2 != 0) {
                        return new MerchantOffer(new ItemStack(currency, this.count), new ItemStack(currency2, count2), itemstack, this.maxUses, this.villagerXp, 0.2F);
                    } else {
                        return new MerchantOffer(new ItemStack(currency, this.count), itemstack, this.maxUses, this.villagerXp, 0.2F);
                    }
                } else {
                    return null;
                }
            }
        }
    }

    public static class CurrencyForItemsTrade implements VillagerTrades.ITrade {
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

    public static class ItemsForCurrencyTrade implements VillagerTrades.ITrade {
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
}
