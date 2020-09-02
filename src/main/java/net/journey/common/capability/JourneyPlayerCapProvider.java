package net.journey.common.capability;

import net.journey.api.capability.JourneyPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class JourneyPlayerCapProvider implements ICapabilitySerializable<NBTBase> {
    private final JourneyPlayer capInstance = JCapabilityManager.getJourneyPlayerCap().getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == JCapabilityManager.getJourneyPlayerCap();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        Capability<JourneyPlayer> playerCap = JCapabilityManager.getJourneyPlayerCap();
        return capability == playerCap ? playerCap.cast(capInstance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        Capability<JourneyPlayer> playerCap = JCapabilityManager.getJourneyPlayerCap();

        return playerCap.getStorage().writeNBT(playerCap, capInstance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        Capability<JourneyPlayer> playerCap = JCapabilityManager.getJourneyPlayerCap();
        playerCap.getStorage().readNBT(playerCap, capInstance, null, nbt);
    }

}