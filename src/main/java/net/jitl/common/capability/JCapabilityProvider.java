package net.jitl.common.capability;

import net.jitl.JITL;
import net.jitl.common.capability.armorability.ArmorSetCapability;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.capability.currentstructure.CurrentStructureCapability;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.capability.player.IJPlayer;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.pressedkeys.IPressedKeysCapability;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.common.capability.owner.attach.CoffeeCapabilityProvider;

//TODO merge with JCapabilities
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
        event.register(IJPlayer.class);
    }

    @Mod.EventBusSubscriber(modid = JITL.MODID)
    public static class Init {
        @SubscribeEvent
        public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
            Entity entity = event.getObject();
            if (!entity.level.isClientSide()) {
                if (entity instanceof LivingEntity) {
                    if (entity instanceof Player player) {
                        event.addCapability(JITL.rl("jitl_player_data"), new CoffeeCapabilityProvider<>(new JPlayer(player)));
                    }
                    event.addCapability(JITL.rl("current_armor"), new CoffeeCapabilityProvider<>(new ArmorSetCapability()));
                    event.addCapability(JITL.rl("current_structure"), new CoffeeCapabilityProvider<>(new CurrentStructureCapability()));
                    event.addCapability(JITL.rl("pressed_keys"), new CoffeeCapabilityProvider<>(new PressedKeysCapability()));
                }
            }
        }
    }
}
