package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.init.JStructures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.*;
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
            List<VillagerTrades.ITrade> trades = event.getTrades().get(1);
            trades.add(new MapTrade(Items.EMERALD, 13, Items.COMPASS, 1, JStructures.ILlAGER_BUNKER.getStructure(), MapDecoration.Type.BANNER_LIGHT_GRAY, 12, 5));
            //trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(JItems.SAPPHIRE, 1), 1, 1, 0));
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
}
