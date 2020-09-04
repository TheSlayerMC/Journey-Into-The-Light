package net.journey.client;

import net.journey.api.block.FeatureProvider;
import net.journey.blocks.util.Feature;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ModelRegistry {
	private static final IStateMapper ONLY_TESR_STATE_MAPPER = new StateMapperBase() {
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()), "dummy");
		}
	};

	@SubscribeEvent
	public static void onModelRegEvent(ModelRegistryEvent event) {
		for (Item i : JourneyItems.items) {
			registerModel(i);
		}

		for (Block b : JourneyBlocks.blocks) {
			if (b instanceof FeatureProvider) {
				Feature feature = ((FeatureProvider) b).getExtraFeatures();

				if (feature.getTeisr() != null) {
					Item.getItemFromBlock(b).setTileEntityItemStackRenderer(feature.getTeisr().get());
				}

				if (feature.isRegDummyVariantBlockState()) {
					ModelLoader.setCustomStateMapper(b, ONLY_TESR_STATE_MAPPER);
				}
			}
		}

		for (Item itemBlock : JourneyBlocks.itemBlocks) {
			registerModel(itemBlock);
		}
	}

	private static void registerModel(Item item) {
		Block block = Block.getBlockFromItem(item);

		ResourceLocation modelLocation = null;
		if (block != Blocks.AIR) {
			if (block instanceof FeatureProvider) {
				Feature feature = ((FeatureProvider) block).getExtraFeatures();

				if (feature.getItemModelLocation() != null) {
					modelLocation = feature.getItemModelLocation();
				}
			}

			if (modelLocation == null) {
				modelLocation = block.getRegistryName();
			}
		} else {
			if (item instanceof FeatureProvider) {
				Feature feature = ((FeatureProvider) item).getExtraFeatures();

				if (feature.getItemModelLocation() != null) {
					modelLocation = feature.getItemModelLocation();
				}
			}

			if (modelLocation == null) {
				modelLocation = item.getRegistryName();
			}
		}

		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(modelLocation, "inventory"));
	}
}