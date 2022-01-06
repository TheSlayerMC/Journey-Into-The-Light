package net.jitl.common.capability;

import net.jitl.JITL;
import net.jitl.common.capability.armorability.ArmorSetCapability;
import net.jitl.common.capability.armorability.ArmorSetStorage;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureStorage;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.capability.pressedkeys.IPressedKeysCapability;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.jitl.common.capability.pressedkeys.PressedKeysStorage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Hacks;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class JCapabilityProvider implements ICapabilitySerializable<Tag> {
    @CapabilityInject(IArmorSetCapability.class)
    public static final Capability<IArmorSetCapability> ARMOR = Hacks.promise();

    private final IArmorSetCapability armorInstance = ARMOR.getDefaultInstance();

    @CapabilityInject(ICurrentStructureCapability.class)
    public static final Capability<ICurrentStructureCapability> STRUCTURE = Hacks.promise();

    private final ICurrentStructureCapability structureInstance = STRUCTURE.getDefaultInstance();

    @CapabilityInject(IPressedKeysCapability.class)
    public static final Capability<IPressedKeysCapability> KEYS = Hacks.promise();

    private final IPressedKeysCapability keysInstance = KEYS.getDefaultInstance();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ARMOR) return armorInstance != null ? LazyOptional.of(() -> armorInstance).cast() : LazyOptional.empty();
        if (cap == STRUCTURE) return structureInstance != null ? LazyOptional.of(() -> structureInstance).cast() : LazyOptional.empty();
        if (cap == KEYS) return keysInstance != null ? LazyOptional.of(() -> keysInstance).cast() : LazyOptional.empty();
        return LazyOptional.empty();
    }

    public static <T> T getCapability(ICapabilityProvider holder, @NotNull Capability<T> cap) {
        return holder.getCapability(cap).resolve().orElse(null);
    }

    @Override
    public Tag serializeNBT() {
        return armorInstance.getNBT();
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        armorInstance.setNBT((CompoundTag) nbt);
    }

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IArmorSetCapability.class, new ArmorSetStorage(), ArmorSetCapability::new);
        CapabilityManager.INSTANCE.register(ICurrentStructureCapability.class, new CurrentStructureStorage(), CurrentStructureCapability::new);
        CapabilityManager.INSTANCE.register(IPressedKeysCapability.class, new PressedKeysStorage(), PressedKeysCapability::new);
    }

    @SubscribeEvent()
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (!entity.level.isClientSide()) {
            if (entity instanceof LivingEntity) {
                if (entity instanceof Player) {
                    event.addCapability(JITL.rl("journey_player_data"), new JCapabilityProvider());
                }
                event.addCapability(JITL.rl("current_armor"), new JCapabilityProvider());
            }
        }
    }
}
