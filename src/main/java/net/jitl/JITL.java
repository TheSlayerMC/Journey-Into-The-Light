package net.jitl;

import net.jitl.client.eventhandler.ClientEventHandler;
import net.jitl.client.eventhandler.ClientLoadingEventHandler;
import net.jitl.common.helper.JourneyContainers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.timeconqueror.timecore.api.TimeMod;

@Mod(JITL.MODID)
public class JITL implements TimeMod {
    public static final String MODID = "jitl";
    public static final String NAME = "Journey Into the Light";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public JITL() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        Registration.register(modEventBus);

        modEventBus.addListener(this::preInit);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new JourneyContainers());

        ClientLoadingEventHandler.regToBus(modEventBus, forgeEventBus);
        ClientEventHandler.regToBus(modEventBus, forgeEventBus);
    }

    private void preInit(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}