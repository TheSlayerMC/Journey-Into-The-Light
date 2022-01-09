package net.jitl.common.capability;

import net.jitl.JITL;
import net.jitl.common.capability.armorability.ArmorSetCapability;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureCapability;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.capability.pressedkeys.IPressedKeysCapability;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

//TODO duplicate of JCapabilities
@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JCapabilityProvider {

    public static final Capability<IArmorSetCapability> ARMOR = CapabilityManager.get(new CapabilityToken<>() { });
    public static final Capability<ICurrentStructureCapability> STRUCTURE = CapabilityManager.get(new CapabilityToken<>() { });
    public static final Capability<IPressedKeysCapability> KEYS = CapabilityManager.get(new CapabilityToken<>() { });

    public static <T> T getCapability(ICapabilityProvider holder, @NotNull Capability<T> cap) {
        return holder.getCapability(cap).resolve().orElse(null);
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IArmorSetCapability.class);
        event.register(ICurrentStructureCapability.class);
        event.register(IPressedKeysCapability.class);

        //CapabilityManager.INSTANCE.register(IArmorSetCapability.class, new ArmorSetStorage(), ArmorSetCapability::new);
       // CapabilityManager.INSTANCE.register(ICurrentStructureCapability.class, new CurrentStructureStorage(), CurrentStructureCapability::new);
        //CapabilityManager.INSTANCE.register(IPressedKeysCapability.class, new PressedKeysStorage(), PressedKeysCapability::new);
    }

    @SubscribeEvent()
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (!entity.level.isClientSide()) {
            if (entity instanceof LivingEntity) {
                if (entity instanceof Player) {
                   // event.addCapability(JITL.rl("journey_player_data"), new JCapabilityProvider());
                }
                event.addCapability(JITL.rl("current_armor"), new ArmorSetProvider(new ArmorSetCapability()));
            }
        }
    }
}
