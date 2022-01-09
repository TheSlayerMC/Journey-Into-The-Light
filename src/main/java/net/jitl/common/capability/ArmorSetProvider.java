package net.jitl.common.capability;

import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmorSetProvider implements ICapabilitySerializable<CompoundTag> {

    private final IArmorSetCapability cap;

    public ArmorSetProvider(IArmorSetCapability cap) {
        this.cap = cap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return LazyOptional.of(() -> (T)this.cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        return cap.getNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cap.setNBT(nbt);
    }
}
