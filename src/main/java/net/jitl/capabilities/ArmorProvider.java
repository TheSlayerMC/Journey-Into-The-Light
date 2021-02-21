package net.jitl.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmorProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(ArmorManager.class)
    public static Capability ARMOR = null;

    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ARMOR) {
            return LazyOptional.of(() -> ARMOR).cast();
        }
        return null;
    }

    @Override
    public INBT serializeNBT() {
        return new CompoundNBT(); //nothing to save
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        //nothing to load
    }
}
