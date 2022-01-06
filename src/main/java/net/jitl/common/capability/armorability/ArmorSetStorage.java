package net.jitl.common.capability.armorability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

import Tag;

public class ArmorSetStorage implements Capability.IStorage<IArmorSetCapability> {

    @Nullable
    @Override
    public Tag writeNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side) {
        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<IArmorSetCapability> capability, IArmorSetCapability instance, Direction side, Tag nbt) {
        instance.setNBT((CompoundTag) nbt);
    }
}
