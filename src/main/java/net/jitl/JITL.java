package net.jitl;

import net.jitl.init.JFeatureGen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
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
        Registration.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void preInit(final FMLCommonSetupEvent event) {
        JFeatureGen.init(event);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    public static ResourceLocation resourceLocation(String location) {
        return new ResourceLocation(MODID, location);
    }
}