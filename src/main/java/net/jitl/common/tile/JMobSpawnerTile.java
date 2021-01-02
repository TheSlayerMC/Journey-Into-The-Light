package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

import javax.annotation.Nullable;

public class JMobSpawnerTile extends TileEntity implements ITickableTileEntity {

    private final AbstractSpawner spawner = new AbstractSpawner() {
        public void broadcastEvent(int id) {
            JMobSpawnerTile.this.level.blockEvent(JMobSpawnerTile.this.worldPosition, Blocks.SPAWNER, id, 0);
        }

        public World getLevel() {
            return JMobSpawnerTile.this.level;
        }

        public BlockPos getPos() {
            return JMobSpawnerTile.this.worldPosition;
        }

        public void setNextSpawnData(WeightedSpawnerEntity nextSpawnData) {
            super.setNextSpawnData(nextSpawnData);
            if (this.getLevel() != null) {
                BlockState blockstate = this.getLevel().getBlockState(this.getPos());
                this.getLevel().sendBlockUpdated(JMobSpawnerTile.this.worldPosition, blockstate, blockstate, 4);
            }

        }
    };

    public JMobSpawnerTile() {
        super(JTiles.MOB_SPAWNER);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.spawner.load(nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        this.spawner.save(compound);
        return compound;
    }

    @Override
    public void tick() {
        this.spawner.tick();
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundnbt = this.save(new CompoundNBT());
        compoundnbt.remove("SpawnPotentials");
        return compoundnbt;
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return this.spawner.onEventTriggered(id) || super.triggerEvent(id, type);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public AbstractSpawner getSpawner() {
        return this.spawner;
    }
}
