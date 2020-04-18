package net.journey.blocks.tileentity;

import net.journey.init.items.JourneyItems;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySenterianAltar extends TileEntity {

	public Item orb;
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("orb", Item.getIdFromItem(orb));
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        orb = Item.getItemById(nbt.getInteger("orb"));
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new SPacketUpdateTileEntity(pos, 1, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
	
	public void putInOrb(Item orb) {
		this.orb = orb;
	}
	
	public Item getOrbItem() {
		return orb;
	}
	
	public boolean getHasOrb() {
		return orb == JourneyItems.sapphire;
	}
}
