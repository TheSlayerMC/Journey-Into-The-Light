package net.jitl.common.block.base;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class JSpawnerBlock extends SpawnerBlock {

    protected final EntityType<? extends CreatureEntity> entity;

    public JSpawnerBlock(EntityType<? extends CreatureEntity> mob) {
        super(JBlockProperties.SPAWNER_PROPS.create());
        entity = mob;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        JMobSpawnerTile spawner = new JMobSpawnerTile();
        spawner.getSpawner().setEntityId(entity);
        return spawner;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(Item.byBlock(this));
    }
}
