package net.jitl;

import net.jitl.client.eventhandler.ClientEventHandler;
import net.jitl.client.eventhandler.ClientLoadingEventHandler;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.init.JourneyBiomeRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.timeconqueror.timecore.api.TimeMod;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.ItemModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;
import top.theillusivec4.curios.Curios;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

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
		modEventBus.addListener(this::enqueue);

	    MinecraftForge.EVENT_BUS.register(this);

	    ClientLoadingEventHandler.regToBus(modEventBus, forgeEventBus);
	    ClientEventHandler.regToBus(modEventBus, forgeEventBus);
    }

	private void preInit(final FMLCommonSetupEvent event) {
		event.enqueueWork(JourneyBiomeRegistry::registerProviders);
		JCapabilityProvider.registerCapabilities();
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		JEntityRenderRegistry.registerEntityRenders();
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	private void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("heart_container").icon(rl("gui/curios/heart_container")).priority(1).size(2).build());
		InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(2).build());
		InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
	}

	public static ResourceLocation rl(String path) {
		return new ResourceLocation(MODID, path);
	}

	public static BlockModelLocation bml(String path) {
		return new BlockModelLocation(MODID, path);
	}

	public static ItemModelLocation iml(String path) {
		return new ItemModelLocation(MODID, path);
	}

	public static TextureLocation tl(String path) {
		return new TextureLocation(MODID, path);
	}

	public static TextureLocation blockTl(String path) {
		return new TextureLocation(MODID, "block/" + path);
	}

	public static TextureLocation itemTl(String path) {
		return new TextureLocation(MODID, "item/" + path);
	}
}