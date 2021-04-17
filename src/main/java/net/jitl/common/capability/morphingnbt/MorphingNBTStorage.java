package net.jitl.common.capability.morphingnbt;

import net.jitl.JITL;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

public class MorphingNBTStorage implements Capability.IStorage<IMorphingNBTCapability> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IMorphingNBTCapability> capability, IMorphingNBTCapability instance, Direction side) {
        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<IMorphingNBTCapability> capability, IMorphingNBTCapability instance, Direction side, INBT nbt) {
        instance.setNBT((CompoundNBT) nbt);
    }
}
