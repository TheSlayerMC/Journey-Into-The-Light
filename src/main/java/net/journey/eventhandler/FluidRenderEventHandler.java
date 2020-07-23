package net.journey.eventhandler;

import net.journey.JITL;
import net.journey.api.fluid.JFluidStateMapper;
import net.journey.init.blocks.JourneyFluids;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = JITL.MOD_ID)
public class FluidRenderEventHandler {

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void initFluidModels(final ModelRegistryEvent event) {
		registerFluidModels(
				JourneyFluids.fluid_acid);
	}

	public static void registerFluidModels(Fluid... fluids) {
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
				System.out.println("STATE MAPPER ");
				ModelLoader.setCustomStateMapper(block, mapper);
			}
		}
	}
}