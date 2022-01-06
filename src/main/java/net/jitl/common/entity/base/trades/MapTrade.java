package net.jitl.common.entity.base.trades;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public class MapTrade implements VillagerTrades.ItemListing {
    private final Item currency;
    private final int count;
    private final Item currency2;
    private final int count2;
    private final StructureFeature<?> destination;
    private final MapDecoration.Type destinationType;
    private final int maxUses;
    private final int villagerXp;

    public MapTrade(Item currency, int count, Item currency2, int count2, StructureFeature<?> structureName, MapDecoration.Type mapDecorationType, int maxUses, int xpValue) {
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
        if (!(trader.level instanceof ServerLevel)) {
            return null;
        } else {
            ServerLevel serverworld = (ServerLevel) trader.level;
            BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, trader.blockPosition(), 100, true);
            if (blockpos != null) {
                ItemStack itemstack = MapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
                MapItem.renderBiomePreviewMap(serverworld, itemstack);
                MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                itemstack.setHoverName(new TranslatableComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
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
