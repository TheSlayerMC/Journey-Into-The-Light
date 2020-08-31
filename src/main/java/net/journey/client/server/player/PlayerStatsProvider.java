package net.journey.client.server.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerStatsProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPlayerStats.class)
    public static final Capability<IPlayerStats> PLAYER_STATS_CAP = null;

    private IPlayerStats INSTANCE = PLAYER_STATS_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == PLAYER_STATS_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == PLAYER_STATS_CAP ? PLAYER_STATS_CAP.cast(this.INSTANCE) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return PLAYER_STATS_CAP.getStorage().writeNBT(PLAYER_STATS_CAP, INSTANCE, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        PLAYER_STATS_CAP.getStorage().readNBT(PLAYER_STATS_CAP, INSTANCE, null, nbt);
    }
}