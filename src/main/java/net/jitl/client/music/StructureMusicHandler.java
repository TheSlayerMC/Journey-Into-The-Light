package net.jitl.client.music;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.guardian.GuardianTowerStructure;
import net.jitl.init.JSounds;
import net.jitl.init.JStructurePieces;
import net.jitl.init.JStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class StructureMusicHandler {
    public static MusicStructure currentMusic;
    //handler for all GUI-related music (might be removed if there isn't any more GUI music)
    @SubscribeEvent()
    public static void structureMusicTick(TickEvent.ClientTickEvent event) {
        if (currentMusic != null) {
            JMusicTicker.addTrack(currentMusic.music, currentMusic.priority, currentMusic.minDuration, currentMusic.maxDuration);
        }
    }

        public enum MusicStructure {
        GUARDIAN_TOWER(JStructures.GUARDIAN_TOWER_HOLDER.getStructure(), JSounds.TOWER_THEME.get(), 5, 2, 5, 1);
        private final Structure structure;
        private final SoundEvent music;
        private final int priority;
        private final int minDuration;
        private final int maxDuration;
        private final int id;

        MusicStructure(Structure structureIn, SoundEvent event, int musicPriority, int musicMin, int musicMax, int musicID) {
            structure = structureIn;
            music = event;
            priority = musicPriority;
            minDuration = musicMin;
            maxDuration = musicMax;
            id = musicID;
        }

        public Structure getStructure() {
            return structure;
        }

        public int getID() {
            return id;
        }

        public static MusicStructure getFromID(int id) {
            return MusicStructure.GUARDIAN_TOWER;
        }
    }
}
