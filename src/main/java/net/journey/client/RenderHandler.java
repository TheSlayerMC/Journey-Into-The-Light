package net.journey.client;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

@EventBusSubscriber
public class RenderHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRender(ModelRegistryEvent event) {
		for(Item i : JourneyItems.items) 
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
		
		for(Block b : JourneyBlocks.blocks) 
			ModelLoader.setCustomModelResourceLocation(SlayerAPI.toItem(b), 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
	}
}