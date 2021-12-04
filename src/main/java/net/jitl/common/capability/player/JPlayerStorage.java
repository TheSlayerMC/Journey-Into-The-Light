package net.jitl.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

//TODO remove somehow because it is useless for coffee system
public class JPlayerStorage implements Capability.IStorage<JPlayer> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<JPlayer> capability, JPlayer instance, Direction side) {
        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<JPlayer> capability, JPlayer instance, Direction side, INBT nbt) {

    }
}
