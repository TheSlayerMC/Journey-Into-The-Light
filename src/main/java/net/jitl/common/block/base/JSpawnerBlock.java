package net.jitl.common.block.base;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JTiles;
import net.jitl.core.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class JSpawnerBlock extends SpawnerBlock {

    protected final EntityType<?> entity;

    public JSpawnerBlock(EntityType<?> mob) {
        super(JBlockProperties.SPAWNER_PROPS.create());
        entity = mob;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        JMobSpawnerTile spawner = new JMobSpawnerTile(pos, state);
        spawner.getSpawner().setEntityId(entity);
        return spawner;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, BlockState state_, BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JTiles.MOB_SPAWNER, level_.isClientSide ? JMobSpawnerTile::clientTick : JMobSpawnerTile::serverTick);
    }
}
