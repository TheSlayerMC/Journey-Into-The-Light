package net.jitl.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

//TODO remove somehow because it is useless for coffee system
public class JPlayerStorage implements Capability.IStorage<IJPlayer> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IJPlayer> capability, IJPlayer instance, Direction side) {
        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<IJPlayer> capability, IJPlayer instance, Direction side, INBT nbt) {

    }
}
