package net.jitl.client.eventhandler.music;

import net.jitl.JITL;
import net.jitl.common.helper.JMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class JMusicTicker {
    //TODO: rewrite all of this
    private static final Minecraft MINECRAFT = Minecraft.getInstance();
    private static ISound currentTrack;
    private static JMusic shouldPlayTrack;
    private static int timeToNext;
    private static final Random RANDOM = new Random();

    @SubscribeEvent()
    public static void musicTick(TickEvent.ClientTickEvent musicEvent) {
        if (musicEvent.phase == TickEvent.Phase.START) {
            shouldPlayTrack = null; //make sure the music stops alongside whatever was playing it
        } else if (!MINECRAFT.isPaused()) {
            if ((currentTrack == null) != (shouldPlayTrack == null)) { //make sure both either are or aren't null
                switchTracks();
            }
            if (currentTrack != null) { //needs to be checked again since previous statement might have made one of them null, causing a crash
                if (currentTrack.getLocation() != shouldPlayTrack.getEvent().getLocation()) { //make sure the music that is playing is actually the correct one
                    switchTracks();
                }
                MINECRAFT.getMusicManager().nextSongDelay = 100; //freeze vanilla music counter so only jitl music will play. Sorry, C418! :D
                if (!MINECRAFT.getSoundManager().isActive(currentTrack)) { //music loop
                    if (timeToNext <= 0) {
                        MINECRAFT.getSoundManager().play(currentTrack);
                        timeToNext = MathHelper.nextInt(RANDOM, shouldPlayTrack.getMin(), shouldPlayTrack.getMax());
                        MINECRAFT.getMusicManager().stopPlaying(); //kills vanilla music
                    } else {
                        timeToNext--;
                    }
                }
            }
        }
    }

    public static void addTrack(JMusic track) {
        if (shouldPlayTrack == null || track.getMusicImportance() > shouldPlayTrack.getMusicImportance()) {
            shouldPlayTrack = track;
        }
    }

    private static void switchTracks() {
        MINECRAFT.getSoundManager().stop(currentTrack);
        if (shouldPlayTrack != null) {
            currentTrack = SimpleSound.forMusic(shouldPlayTrack.getEvent());
        } else {
            currentTrack = null;
            BackgroundMusicSelector vanillaMusic = MINECRAFT.getSituationalMusic();
            MINECRAFT.getMusicManager().nextSongDelay = (MathHelper.nextInt(RANDOM, 0, vanillaMusic.getMinDelay() / 2)); //recreates a vanilla music swap
        }
    }

    public static boolean isMusicPlaying(SoundEvent event) {
        return (currentTrack != null && event.getLocation() == currentTrack.getLocation() && MINECRAFT.getSoundManager().isActive(currentTrack));
    }
}