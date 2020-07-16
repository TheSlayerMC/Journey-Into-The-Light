package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = JITL.MOD_ID)
public class TCClientHandler {

	@SubscribeEvent
	public static void initFluidModels(final ModelRegistryEvent event) {
		registerFluidModels(TCFluids.CELESTIUM);
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
				ModelLoader.setCustomStateMapper(block, mapper);
			}
		}
	}

	public static class JFluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

		public final Fluid fluid;
		public final ModelResourceLocation location;

		public JFluidStateMapper(Fluid fluid) {
			this.fluid = fluid;
			this.location = new ModelResourceLocation(new ResourceLocation(JITL.MOD_ID, "fluid_block"), fluid.getName());
		}

		@Nonnull
		@Override
		protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
			return location;
		}

		@Nonnull
		@Override
		public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
			return location;
		}
	}
}
