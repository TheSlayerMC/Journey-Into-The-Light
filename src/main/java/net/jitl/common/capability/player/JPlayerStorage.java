package net.jitl.common.capability.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

//TODO remove somehow because it is useless for coffee system
import Tag;

public class JPlayerStorage implements Capability.IStorage<JPlayer> {
    @Nullable
    @Override
    public Tag writeNBT(Capability<JPlayer> capability, JPlayer instance, Direction side) {
        return new CompoundTag();
    }

    @Override
    public void readNBT(Capability<JPlayer> capability, JPlayer instance, Direction side, Tag nbt) {

    }
}
