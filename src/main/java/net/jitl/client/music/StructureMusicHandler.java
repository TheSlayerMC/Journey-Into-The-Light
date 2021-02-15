package net.jitl.client.music;

import net.jitl.JITL;
import net.jitl.common.helper.EnumStructureMusic;
import net.jitl.init.JSounds;
import net.jitl.init.JStructures;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.util.EnumLookup;

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
