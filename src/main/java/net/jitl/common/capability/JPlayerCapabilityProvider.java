package net.jitl.common.capability;

import net.jitl.common.capability.player.IJPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class JPlayerCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private final IJPlayer player;

    public JPlayerCapabilityProvider(IJPlayer player) {
        this.player = player;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.player.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.player.serializeNBT();
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == JCapabilityProvider.PLAYER) {
            return LazyOptional.of(() -> (T) this.player);
        }
        return LazyOptional.empty();
    }
}
