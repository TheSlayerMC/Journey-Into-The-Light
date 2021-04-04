package net.jitl.common.capability.currentstructure;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class CurrentStructureStorage implements Capability.IStorage<ICurrentStructureCapability> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<ICurrentStructureCapability> capability, ICurrentStructureCapability instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability<ICurrentStructureCapability> capability, ICurrentStructureCapability instance, Direction side, INBT nbt) {

    }
}
