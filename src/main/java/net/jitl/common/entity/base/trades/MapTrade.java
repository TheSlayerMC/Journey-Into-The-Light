package net.jitl.common.entity.base.trades;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public class MapTrade implements VillagerTrades.ITrade {
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
