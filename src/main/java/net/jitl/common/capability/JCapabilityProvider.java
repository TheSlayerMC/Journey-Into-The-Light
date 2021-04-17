package net.jitl.common.capability;

import net.jitl.common.capability.armorability.ArmorSetCapability;
import net.jitl.common.capability.armorability.ArmorSetStorage;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureStorage;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.capability.morphingnbt.IMorphingNBTCapability;
import net.jitl.common.capability.morphingnbt.MorphingNBTCapability;
import net.jitl.common.capability.morphingnbt.MorphingNBTStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Hacks;

public class JCapabilityProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IArmorSetCapability.class)
    public static final Capability<IArmorSetCapability> ARMOR = Hacks.promise();

    private final IArmorSetCapability armorInstance = ARMOR.getDefaultInstance();

    @CapabilityInject(ICurrentStructureCapability.class)
    public static final Capability<ICurrentStructureCapability> STRUCTURE = Hacks.promise();

    private final ICurrentStructureCapability structureInstance = STRUCTURE.getDefaultInstance();

    @CapabilityInject(IMorphingNBTCapability.class)
    public static final Capability<IMorphingNBTCapability> NBT = Hacks.promise();

    private final IMorphingNBTCapability nbtInstance = NBT.getDefaultInstance();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ARMOR && armorInstance != null) {
            return LazyOptional.of(() -> armorInstance).cast();
        } else if (cap == STRUCTURE && structureInstance != null) {
            return LazyOptional.of(() -> structureInstance).cast();
        } else if (cap == NBT && nbtInstance != null) {
            return LazyOptional.of(() -> nbtInstance).cast();
        }
        return LazyOptional.empty();
    }

    //TODO: Look into creating method to get capabilities more easily

    @Override
    public INBT serializeNBT() {
        return new CompoundNBT(); //nothing to save
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        //nothing to load
    }

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IArmorSetCapability.class, new ArmorSetStorage(), ArmorSetCapability::new);
        CapabilityManager.INSTANCE.register(ICurrentStructureCapability.class, new CurrentStructureStorage(), CurrentStructureCapability::new);
        CapabilityManager.INSTANCE.register(IMorphingNBTCapability.class, new MorphingNBTStorage(), MorphingNBTCapability::new);
    }
}
