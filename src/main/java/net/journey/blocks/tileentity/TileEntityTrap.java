package net.journey.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ITickable;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTrap extends TileEntity implements ITickable {
	
    private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {	
        @Override
		public void broadcastEvent(int id) {
            TileEntityTrap.this.world.addBlockEvent(TileEntityTrap.this.pos, Blocks.MOB_SPAWNER, id, 0);
        }
        
        @Override
        public World getSpawnerWorld() {
            return TileEntityTrap.this.world;
        }
        
        @Override
        public BlockPos getSpawnerPosition() {
            return TileEntityTrap.this.pos;
        }
        
        @Override
        public void setNextSpawnData(WeightedSpawnerEntity s) {
            super.setNextSpawnData(s);

            if(this.getSpawnerWorld() != null) {
                IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
                this.getSpawnerWorld().notifyBlockUpdate(TileEntityTrap.this.pos, iblockstate, iblockstate, 4);
            }
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.spawnerLogic.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.spawnerLogic.writeToNBT(compound);
        return compound;
    }

    @Override
    public void update(){
        this.spawnerLogic.updateSpawner();
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        nbttagcompound.removeTag("SpawnPotentials");
        return new SPacketUpdateTileEntity(this.pos, 1, nbttagcompound);
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
    }

    public MobSpawnerBaseLogic getSpawnerBaseLogic() {
        return this.spawnerLogic;
    }
}