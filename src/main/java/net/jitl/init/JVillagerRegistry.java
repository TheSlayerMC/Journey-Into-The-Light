package net.jitl.init;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.JITL;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class JVillagerRegistry {

    public static final DeferredRegister<PointOfInterestType> POI_REGISTER = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final DeferredRegister<VillagerProfession> PROFESSION_REGISTER = DeferredRegister.create(ForgeRegistries.PROFESSIONS, JITL.MODID);

    public static final RegistryObject<PointOfInterestType> TEST_POI = POI_REGISTER.register("test",
            () -> new PointOfInterestType("test", PointOfInterestType.getBlockStates(JBlocks.FROZEN_PORTAL_FRAME), 1, 1));

    public static final RegistryObject<VillagerProfession> TEST_PROFESSION = PROFESSION_REGISTER.register("test",
            () -> new VillagerProfession("test", TEST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));

    public static void addTrades() {
        VillagerTrades.ITrade[] testLevel1 = new VillagerTrades.ITrade[]{
                new CurrencyForItemsTrade(JItems.LUNIUM_INGOT, JItems.LUNIUM_POWDER, 1, 1, 1),
                new ItemsForCurrencyTrade(Items.SEA_LANTERN, JItems.CELESTIUM_INGOT, 1, 1, 1, 1)
        };
        VillagerTrades.TRADES.put(TEST_PROFESSION.get(), toIntMap(ImmutableMap.of(1, testLevel1)));
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

    private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> immutableMap) {
        return new Int2ObjectOpenHashMap<>(immutableMap);
    }
}
