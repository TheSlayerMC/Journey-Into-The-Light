package net.jitl.client.eventhandler.music;

import net.jitl.common.helper.EnumStructureMusic;
import net.jitl.core.JITL;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class StructureMusicHandler {
    public static EnumStructureMusic currentMusic;
    //handler for all GUI-related music (might be removed if there isn't any more GUI music)
    @SubscribeEvent()
    public static void structureMusicTick(TickEvent.ClientTickEvent event) {
        if (currentMusic != null) {
            JMusicTicker.addTrack(currentMusic.getMusicForStructure());
        }
    }
}
