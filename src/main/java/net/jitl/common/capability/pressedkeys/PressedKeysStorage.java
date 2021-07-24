package net.jitl.common.capability.pressedkeys;

import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class PressedKeysStorage implements Capability.IStorage<IPressedKeysCapability> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IPressedKeysCapability> capability, IPressedKeysCapability instance, Direction side) {
        System.out.println("Write nbt");
        return null;
    }

    @Override
    public void readNBT(Capability<IPressedKeysCapability> capability, IPressedKeysCapability instance, Direction side, INBT nbt) {
        System.out.println("Read nbt");
    }
}
