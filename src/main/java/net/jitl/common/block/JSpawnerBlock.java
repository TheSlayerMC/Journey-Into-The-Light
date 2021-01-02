package net.jitl.common.block;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class JSpawnerBlock extends SpawnerBlock {

    protected final EntityType<? extends CreatureEntity> entity;

    public JSpawnerBlock(EntityType<? extends CreatureEntity> mob) {
        super(JBlockProperties.SPAWNER_PROPS);
        entity = mob;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        JMobSpawnerTile spawner = new JMobSpawnerTile();
        spawner.getSpawner().setEntityId(entity);
        return spawner;
    }

}
