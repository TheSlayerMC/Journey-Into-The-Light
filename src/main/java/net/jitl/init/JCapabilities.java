package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.capability.player.JPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.api.registry.CapabilityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.util.Hacks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JCapabilities {
    @AutoRegistrable
    private static final CapabilityRegister REGISTER = new CapabilityRegister(JITL.MODID);


    @AutoRegistrable.Init
    public static void register() {
        REGISTER.register(JPlayer.class);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //FIXME player cap data
            /*TimeCore.INSTANCE.getCapabilityManager().attachStaticCoffeeCapability(CapabilityOwner.ENTITY, PLAYER, entity -> entity instanceof Player, entity -> new JPlayer((Player) entity));
//            TimeCore.INSTANCE.getCapabilityManager().enableKeepingPlayerCapability(player -> {
//                JPlayer cap = JPlayer.from(player);
//                cap.serialize(coffeeProperty -> {coffeeProperty.})
//            });//TODO make better
            TimeCore.INSTANCE.getCapabilityManager().enableSyncingPlayerCapabilityOnJoin(entity -> {
                JPlayer cap = JPlayer.from(entity);
                if (cap != null) cap.sendAllData();
            });*/
        });
    }
}
