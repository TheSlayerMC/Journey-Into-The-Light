package net.jitl.capabilities.essence;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class EssenceStorage implements Capability.IStorage<IEssenceCapability> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IEssenceCapability> capability, IEssenceCapability instance, Direction side) {
        //TODO: save values
        return null;
    }

    @Override
    public void readNBT(Capability<IEssenceCapability> capability, IEssenceCapability instance, Direction side, INBT nbt) {

    }
}
