package net.journey.blocks.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityTrap extends TileEntity implements ITickable {
    private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
    	
        @Override
        public void func_98267_a(int id) {
            TileEntityTrap.this.worldObj.addBlockEvent(TileEntityTrap.this.pos, Blocks.mob_spawner, id, 0);
        }
        
        @Override
        public World getSpawnerWorld() {
            return TileEntityTrap.this.worldObj;
        }
        
        @Override
        public BlockPos getSpawnerPosition() {
            return TileEntityTrap.this.pos;
        }
        
        @Override
        public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart e) {
            super.setRandomEntity(e);

            if (this.getSpawnerWorld() != null) {
                this.getSpawnerWorld().markBlockForUpdate(TileEntityTrap.this.pos);
            }
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.spawnerLogic.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.spawnerLogic.writeToNBT(compound);
    }

    @Override
    public void update(){
        this.spawnerLogic.updateSpawner();
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        nbttagcompound.removeTag("SpawnPotentials");
        return new S35PacketUpdateTileEntity(this.pos, 1, nbttagcompound);
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
    }

    @Override
    public boolean func_183000_F() {
        return true;
    }

    public MobSpawnerBaseLogic getSpawnerBaseLogic() {
        return this.spawnerLogic;
    }
}