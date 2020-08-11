package net.journey.client;

import net.journey.api.block.CustomItemModelProvider;
import net.journey.api.block.IHasTeisr;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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

    @SubscribeEvent
    public static void onModelRegEvent(ModelRegistryEvent event) {
        for (Item i : JourneyItems.items) {
            registerModel(i);
        }

        for (Block b : JourneyBlocks.blocks) {
            if (b instanceof IHasTeisr) {
                Item.getItemFromBlock(b).setTileEntityItemStackRenderer(((IHasTeisr) b).createTeisr().get());
            }
        }

        for (Item itemBlock : JourneyBlocks.itemBlocks) {
            registerModel(itemBlock);
        }
    }

    private static void registerModel(Item item) {
        Block block = Block.getBlockFromItem(item);

        ResourceLocation modelLocation;
        if (block != Blocks.AIR) {
            if (block instanceof CustomItemModelProvider) {
                modelLocation = ((CustomItemModelProvider) block).getItemModelResourceLocation();
            } else {
                modelLocation = block.getRegistryName();
            }
        } else {
            if (item instanceof CustomItemModelProvider) {
                modelLocation = ((CustomItemModelProvider) item).getItemModelResourceLocation();
            } else {
                modelLocation = item.getRegistryName();
            }
        }

        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(modelLocation, "inventory"));
    }
}