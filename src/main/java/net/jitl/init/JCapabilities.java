package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.capability.player.IJPlayer;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.JPlayerStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.registry.CapabilityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.util.Hacks;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JCapabilities {
    @AutoRegistrable
    private static final CapabilityRegister REGISTER = new CapabilityRegister(JITL.MODID);

    @CapabilityInject(IJPlayer.class)
    public static final Capability<IJPlayer> PLAYER = Hacks.promise();

    @AutoRegistrable.InitMethod
    private static void register() {
        REGISTER.regCapability(IJPlayer.class, new JPlayerStorage());
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TimeCore.INSTANCE.getCapabilityManager().attachStaticCoffeeCapability(CapabilityOwner.ENTITY, PLAYER, entity -> entity instanceof PlayerEntity, entity -> new JPlayer((PlayerEntity) entity));
            TimeCore.INSTANCE.getCapabilityManager().enableKeepingPlayerCapability(JPlayer::from);
            TimeCore.INSTANCE.getCapabilityManager().enableSyncingPlayerCapabilityOnJoin(entity -> {
                JPlayer cap = JPlayer.from(entity);
                if (cap != null) cap.sendAllData();
            });
        });
    }
}
