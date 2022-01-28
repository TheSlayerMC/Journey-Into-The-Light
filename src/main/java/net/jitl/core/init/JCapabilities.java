package net.jitl.core.init;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.core.JITL;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.registry.CapabilityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JCapabilities {
    @AutoRegistrable
    private static final CapabilityRegister REGISTER = new CapabilityRegister(JITL.MODID);

    public static final Capability<JPlayer> PLAYER = REGISTER.register(JPlayer.class);

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TimeCore.INSTANCE.getCapabilityManager().attachStaticCoffeeCapability(CapabilityOwner.ENTITY, PLAYER, entity -> entity instanceof Player, entity -> new JPlayer((Player) entity));
            //TODO make better
            TimeCore.INSTANCE.getCapabilityManager().enableKeepingPlayerCapability(JPlayer::from);
            TimeCore.INSTANCE.getCapabilityManager().enableSyncingPlayerCapabilityOnJoin(entity -> {
                JPlayer cap = JPlayer.from(entity);
                if (cap != null) cap.sendAllData();
            });
        });
    }
}
