package net.jitl.common.capability.armorability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class ArmorSetStorage implements Capability.IStorage<IArmorSetCapability> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side) {
        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side, INBT nbt) {
        instance.setNBT((CompoundNBT) nbt);
    }
}
