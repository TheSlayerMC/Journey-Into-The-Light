package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.init.JItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class EskimoEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE, Items.COMPASS, 1, 12, 5)
    }));

    public EskimoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ITrade[]> getVillagerTrades() {
        return TRADES;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}
