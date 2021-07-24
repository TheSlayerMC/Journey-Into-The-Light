package net.jitl.common.capability;

import net.jitl.JITL;
import net.jitl.common.capability.armorability.ArmorSetCapability;
import net.jitl.common.capability.armorability.ArmorSetStorage;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureStorage;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Hacks;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class JCapabilityProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IArmorSetCapability.class)
    public static final Capability<IArmorSetCapability> ARMOR = Hacks.promise();

    private final IArmorSetCapability armorInstance = ARMOR.getDefaultInstance();

    @CapabilityInject(ICurrentStructureCapability.class)
    public static final Capability<ICurrentStructureCapability> STRUCTURE = Hacks.promise();

    private final ICurrentStructureCapability structureInstance = STRUCTURE.getDefaultInstance();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ARMOR && armorInstance != null) {
            return LazyOptional.of(() -> armorInstance).cast();
        } else if (cap == STRUCTURE && structureInstance != null) {
            return LazyOptional.of(() -> structureInstance).cast();
        }
        return LazyOptional.empty();
    }

    //TODO: Look into creating method to get capabilities more easily

    @Override
    public INBT serializeNBT() {
        return armorInstance.getNBT(); //nothing to save
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        armorInstance.setNBT((CompoundNBT) nbt);
    }

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IArmorSetCapability.class, new ArmorSetStorage(), ArmorSetCapability::new);
        CapabilityManager.INSTANCE.register(ICurrentStructureCapability.class, new CurrentStructureStorage(), CurrentStructureCapability::new);
    }

    @SubscribeEvent()
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (!entity.level.isClientSide()) {
            if (entity instanceof LivingEntity) {
                if (entity instanceof PlayerEntity) {
                    event.addCapability(JITL.rl("current_structure"), new JCapabilityProvider());
                }
                event.addCapability(JITL.rl("current_armor"), new JCapabilityProvider());
            }
        }
    }
}
