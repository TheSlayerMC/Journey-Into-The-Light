package net.jitl.capabilities.armorability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class ArmorSetStorage implements Capability.IStorage<IArmorSetCapability> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side, INBT nbt) {

    }
}
