package net.jitl.client.eventhandler;

import net.jitl.JITL;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TextureStitchEventHandler {

    @SubscribeEvent
    public static void stitchTextures(TextureStitchEvent.Pre evt) {
        if (evt.getMap().location() == InventoryMenu.BLOCK_ATLAS) {
            evt.addSprite(JITL.rl("gui/curios/heart_container"));
            evt.addSprite(JITL.rl("gui/curios/catalyst"));
        }
    }
}
