package net.jitl.common.entity.overworld;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.core.init.JItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class MageEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.POWDER_OF_ESSENCIA, 32, JItems.LUNIUM_POWDER, 32, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, JItems.LOOT_POUCH_BASIC, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.LUNIUM_POWDER, 8, JItems.SAPPHIRE, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.FERMENTED_SPIDER_EYE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 10, Items.GUNPOWDER, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 4, Items.REDSTONE, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.PHANTOM_MEMBRANE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.GHAST_TEAR, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 16, Items.MAGMA_CREAM, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 8, Items.GLOWSTONE_DUST, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE, 32, Items.BLAZE_POWDER, 8, 99, 5)
    }));

    public MageEntity(EntityType<? extends JVillagerEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    @Nullable
    @Override
    protected Screen getDialogue() {
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}
