package net.journey.blocks.tileentity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.jetbrains.annotations.NotNull;

public abstract class TileEntitySynced extends TileEntity {

	@Override
	public final void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		readFromNBT(compound);
	}

	@Override
	public @NotNull NBTTagCompound getUpdateTag() {
		NBTTagCompound updateTag = super.getUpdateTag();
		return writeToNBT(updateTag);
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	/**
	 * Saves the block to disk, sends packet to update it on client.
	 */
	public void setBlockToUpdateAndSave() {
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		markDirty();
	}

	/**
	 * Returns the blockstate on tileentity pos.
	 */
	public IBlockState getState() {
		return world.getBlockState(pos);
	}
}
