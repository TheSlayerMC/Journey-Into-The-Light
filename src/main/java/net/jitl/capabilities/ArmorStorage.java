package net.jitl.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class ArmorStorage implements Capability.IStorage {

    @Nullable
    @Override
    public INBT writeNBT(Capability capability, Object instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability capability, Object instance, Direction side, INBT nbt) {

    }
}
