package net.jitl.common.capability.currentstructure;

import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

import Tag;

public class CurrentStructureStorage implements Capability.IStorage<ICurrentStructureCapability> {

    @Nullable
    @Override
    public Tag writeNBT(Capability<ICurrentStructureCapability> capability, ICurrentStructureCapability instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability<ICurrentStructureCapability> capability, ICurrentStructureCapability instance, Direction side, Tag nbt) {

    }
}
