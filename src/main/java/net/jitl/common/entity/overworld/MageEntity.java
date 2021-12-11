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
            new CurrencyForItemsTrade(JItems.LUNIUM_POWDER, JItems.POWDER_OF_ESSENCIA, 1, 12, 5),
            new CurrencyForItemsTrade(JItems.LOOT_POUCH_BASIC, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, JItems.LUNIUM_POWDER, 1, 12, 5),
            new CurrencyForItemsTrade(Items.FERMENTED_SPIDER_EYE, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.GUNPOWDER, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.REDSTONE, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.PHANTOM_MEMBRANE, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.GHAST_TEAR, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.MAGMA_CREAM, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.GLOWSTONE_DUST, JItems.SAPPHIRE, 1, 12, 5),
            new CurrencyForItemsTrade(Items.BLAZE_POWDER, JItems.SAPPHIRE, 1, 12, 5)
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
