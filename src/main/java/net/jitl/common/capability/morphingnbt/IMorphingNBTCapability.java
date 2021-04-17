package net.jitl.common.capability.morphingnbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public interface IMorphingNBTCapability {
    void setNBT(CompoundNBT nbt);

    CompoundNBT getNBT();
}
