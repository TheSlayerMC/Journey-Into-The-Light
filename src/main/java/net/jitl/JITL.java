package net.jitl;

import net.jitl.client.eventhandler.ClientEventHandler;
import net.jitl.client.eventhandler.ClientLoadingEventHandler;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.dimension.Dimensions;
import net.jitl.init.JBiomeRegistry;
import net.jitl.init.JEntities;
import net.jitl.init.JLootConditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.raid.Raid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.timeconqueror.timecore.api.TimeCoreAPI;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.ItemModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(JITL.MODID)
public class JITL {
    public static final String MODID = "jitl";
    public static final String NAME = "Journey Into the Light";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public JITL() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

		Registration.register(modEventBus);
		Dimensions.register(modEventBus);
		JLootConditions.init();

		modEventBus.addListener(this::preInit);
		modEventBus.addListener(this::clientSetup);
		modEventBus.addListener(this::enqueue);

		MinecraftForge.EVENT_BUS.register(this);

		ClientLoadingEventHandler.regToBus(modEventBus, forgeEventBus);
		ClientEventHandler.regToBus(modEventBus, forgeEventBus);

		TimeCoreAPI.setup(this);
	}

	private void preInit(final FMLCommonSetupEvent event) {
		event.enqueueWork(JBiomeRegistry::registerProviders);
		JCapabilityProvider.registerCapabilities();
		Raid.RaiderType.create("illager_mech", JEntities.ILLAGER_MECH_TYPE, new int[]{0, 0, 0, 1, 0, 1, 0, 2}); //TODO: move me
	}

	private void clientSetup(final FMLClientSetupEvent event) {

	}

	private void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("heart_container").icon(rl("gui/curios/heart_container")).priority(1).size(2).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("catalyst").icon(rl("gui/curios/catalyst")).priority(1).size(2).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(2).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
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