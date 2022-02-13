package net.jitl.common.block.base;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.common.tile.spawner.GoldBotSpawnerTile;
import net.jitl.common.tile.spawner.MiniGhastSpawnerTile;
import net.jitl.core.init.JBlocks;
import net.jitl.core.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JSpawnerBlock extends SpawnerBlock {

    protected final EntityType<?> entity;
    protected final BlockEntityType tile;

    public JSpawnerBlock(EntityType<?> mob, BlockEntityType tile) {
        super(JBlockProperties.SPAWNER_PROPS.create());
        this.entity = mob;
        this.tile = tile;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        JMobSpawnerTile spawner = new MiniGhastSpawnerTile(pos, state);
        if(state.getBlock() == JBlocks.MINI_GHAST_SPAWNER)
            spawner = new MiniGhastSpawnerTile(pos, state);
        if(state.getBlock() == JBlocks.GOLD_BOT_SPAWNER)
            spawner = new GoldBotSpawnerTile(pos, state);

        spawner.getSpawner().setEntityId(entity);
        return spawner;
    }

    @NotNull
    @Override
    public ItemStack getCloneItemStack(BlockGetter level_, BlockPos pos_, BlockState state_) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, this.tile, level_.isClientSide ? JMobSpawnerTile::clientTick : JMobSpawnerTile::serverTick);
    }
}
