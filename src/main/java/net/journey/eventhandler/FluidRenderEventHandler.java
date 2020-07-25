package net.journey.eventhandler;

import net.journey.JITL;
import net.journey.api.fluid.JFluidStateMapper;
import net.journey.init.blocks.JourneyFluids;
import net.journey.integration.tconstruct.TCFluidStateMapper;
import net.journey.integration.tconstruct.TCFluids;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = JITL.MOD_ID)
public class FluidRenderEventHandler {

	public static final boolean IS_TCONSTRUCT_LOADED = Loader.isModLoaded("tconstruct");

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void initFluidModels(final ModelRegistryEvent event) {
		registerJFluidModels(
				JourneyFluids.FLUID_ACID);
		JITL.LOGGER.info("Registering " + JITL.MOD_NAME + " fluid models");
		if (IS_TCONSTRUCT_LOADED) {
			registerTCFluidModels(
					TCFluids.LUNIUM,
					TCFluids.SHADIUM,
					TCFluids.BLOODCRUST,
					TCFluids.CELESTIUM,
					TCFluids.MEKYUM,
					TCFluids.STORON,
					TCFluids.KORITE,
					TCFluids.FLAIRIUM,
					TCFluids.DES,
					TCFluids.ORBADITE);
			JITL.LOGGER.info("Detected Tinkers' Construct. Registering " + JITL.MOD_NAME + " fluid models for Tinker's Construct compatibility");
		}
	}

	public static void registerJFluidModels(Fluid... fluids) {
		for (Fluid fluid : fluids) {
			if (fluid == null) {
				return;
			}
			Block block = fluid.getBlock();
			if (block != null) {
				Item item = Item.getItemFromBlock(block);
				JFluidStateMapper mapper = new JFluidStateMapper(fluid);
				// item-model
				if (item != Items.AIR) {
					ModelLoader.registerItemVariants(item);
					ModelLoader.setCustomMeshDefinition(item, mapper);
				}
				// block-model
				ModelLoader.setCustomStateMapper(block, mapper);
			}
		}
	}

	public static void registerTCFluidModels(Fluid... fluids) {
		for (Fluid fluid : fluids) {
			if (fluid == null) {
				return;
			}
			Block block = fluid.getBlock();
			if (block != null) {
				Item item = Item.getItemFromBlock(block);
				TCFluidStateMapper mapper = new TCFluidStateMapper(fluid);
				// item-model
				if (item != Items.AIR) {
					ModelLoader.registerItemVariants(item);
					ModelLoader.setCustomMeshDefinition(item, mapper);
				}
				// block-model
				ModelLoader.setCustomStateMapper(block, mapper);
			}
		}
	}
}