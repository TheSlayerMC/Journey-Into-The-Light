package net.jitl.common.block.base;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.util.JBlockProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

public class JSpawnerBlock extends SpawnerBlock {

    protected final EntityType<? extends PathfinderMob> entity;

    public JSpawnerBlock(EntityType<? extends PathfinderMob> mob) {
        super(JBlockProperties.SPAWNER_PROPS.create());
        entity = mob;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        JMobSpawnerTile spawner = new JMobSpawnerTile();
        spawner.getSpawner().setEntityId(entity);
        return spawner;
    }
}
