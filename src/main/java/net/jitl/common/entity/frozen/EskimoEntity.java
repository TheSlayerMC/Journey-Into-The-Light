package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.init.JItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class EskimoEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE, 1, Items.COMPASS, 1, 12, 5)
    }));

    public EskimoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ITrade[]> getVillagerTrades() {
        return TRADES;
    }
}
