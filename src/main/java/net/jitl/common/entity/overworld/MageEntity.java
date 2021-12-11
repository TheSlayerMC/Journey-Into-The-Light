package net.jitl.common.entity.overworld;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.init.JItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class MageEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new CurrencyForItemsTrade(JItems.POWDER_OF_ESSENCIA, 32, JItems.LUNIUM_POWDER, 32, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, JItems.LOOT_POUCH_BASIC, 1, 12, 5),
            new CurrencyForItemsTrade(JItems.LUNIUM_POWDER, 8, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.FERMENTED_SPIDER_EYE, 2, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 10, Items.GUNPOWDER, 4, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 4, Items.REDSTONE, 8, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.PHANTOM_MEMBRANE, 2, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.GHAST_TEAR, 4, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.MAGMA_CREAM, 8, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 8, Items.GLOWSTONE_DUST, 4, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 32, Items.BLAZE_POWDER, 8, 12, 5)
    }));

    public MageEntity(EntityType<? extends JVillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
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
