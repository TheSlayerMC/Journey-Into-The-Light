package net.jitl.client.eventhandler.music;

import net.jitl.common.helper.JMusic;
import net.jitl.core.JITL;
import net.jitl.core.config.JConfigs;
import net.jitl.core.init.JSounds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GUIMusicHandler {
    //handler for all GUI-related music (might be removed if there isn't any more GUI music)
    @SubscribeEvent()
    public static void guiMusicTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().player == null && JConfigs.CLIENT.GUI_CATEGORY.isJITLMenuEnabled()) {
                JMusicTicker.addTrack(new JMusic(JSounds.MENU_MUSIC.get(), 10, 0, 0));
            }
    }
}
