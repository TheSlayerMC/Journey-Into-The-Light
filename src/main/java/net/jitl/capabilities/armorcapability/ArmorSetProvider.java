package net.jitl.capabilities.armorcapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Hacks;

public class ArmorSetProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IArmorSetCapability.class)
    public static final Capability<IArmorSetCapability> ARMOR = Hacks.promise();

    private final IArmorSetCapability instance = ARMOR.getDefaultInstance();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ARMOR && instance != null) {
            return LazyOptional.of(() -> instance).cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return new CompoundNBT(); //nothing to save
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        //nothing to load
    }
}
