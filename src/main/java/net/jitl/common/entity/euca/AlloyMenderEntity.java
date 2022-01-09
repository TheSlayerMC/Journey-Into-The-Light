package net.jitl.common.entity.euca;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.MapTrade;
import net.jitl.init.world.JStructures;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import javax.annotation.Nullable;

public class AlloyMenderEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new MapTrade(Items.EMERALD, 15, Items.COMPASS, 1, JStructures.ILlAGER_BUNKER.getStructure(), MapDecoration.Type.BANNER_LIGHT_GRAY, 12, 5)
    }));

    public AlloyMenderEntity(EntityType<? extends Villager> type, Level worldIn) {
        super(type, worldIn);
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
}
