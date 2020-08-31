package net.journey.client.server.player;

import net.journey.client.server.EssenceBar;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class PlayerStatsSerializer implements IStorage<IPlayerStats> {

	@Nullable
	@Override
	public NBTBase writeNBT(Capability<IPlayerStats> capability, IPlayerStats instance, EnumFacing side) {
		if (!(instance instanceof PlayerStats)) {
			throw new IllegalArgumentException("Can not serialize an instance that isn't the default implementation");
		}
		return PlayerStats.writeToNBT((PlayerStats) instance);
	}

	@Override
	public void readNBT(Capability<IPlayerStats> capability, IPlayerStats instance, EnumFacing side, NBTBase nbt) {
		if (!(instance instanceof IPlayerStats)) {
			throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
		}

		PlayerStats stats = (PlayerStats) instance;

		if(nbt instanceof NBTTagInt) {
			PlayerStats.readFromOldNBT(stats, (NBTTagInt) nbt);
        } else {
        	PlayerStats.readFromNBT(stats, ((NBTTagCompound) nbt));
        }
	}
}