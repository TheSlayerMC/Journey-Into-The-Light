package net.jitl.common.capability.pressedkeys;

import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

import Tag;

public class PressedKeysStorage implements Capability.IStorage<IPressedKeysCapability> {
    @Nullable
    @Override
    public Tag writeNBT(Capability<IPressedKeysCapability> capability, IPressedKeysCapability instance, Direction side) {
        System.out.println("Write nbt");
        return null;
    }

    @Override
    public void readNBT(Capability<IPressedKeysCapability> capability, IPressedKeysCapability instance, Direction side, Tag nbt) {
        System.out.println("Read nbt");
    }
}
