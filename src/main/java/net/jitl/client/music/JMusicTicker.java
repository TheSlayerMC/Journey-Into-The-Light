package net.jitl.client.music;

import net.jitl.JITL;
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
    private static final Minecraft MINECRAFT = Minecraft.getInstance();
    private static ISound currentTrack;
    private static ISound shouldPlayTrack;
    private static int currentPriority;
    private static int lowestTimeToNext;
    private static int highestTimeToNext;
    private static int timeToNext;
    private static final Random RANDOM = new Random();

    @SubscribeEvent()
    public static void musicTick(TickEvent.ClientTickEvent musicEvent) {
        if (musicEvent.phase == TickEvent.Phase.START) {
            shouldPlayTrack = null; //make sure the music stops alongside whatever was playing it
            currentPriority = 0;
        } else if (!MINECRAFT.isPaused()) {
            if ((currentTrack == null) != (shouldPlayTrack == null)) { //make sure both either are or aren't null
                switchTracks();
            }
            if (currentTrack != null) { //needs to be checked again since previous statement might have made one of them null, causing a crash
                if (currentTrack.getLocation() != shouldPlayTrack.getLocation()) { //make sure the music that is playing is actually the correct one
                    switchTracks();
                }
                MINECRAFT.getMusicManager().nextSongDelay = 100; //freeze vanilla music counter so only jitl music will play. Sorry, C418! :D
                if (!MINECRAFT.getSoundManager().isActive(currentTrack)) { //music loop
                    if (timeToNext <= 0) {
                        MINECRAFT.getSoundManager().play(currentTrack);
                        timeToNext = MathHelper.nextInt(RANDOM, lowestTimeToNext, highestTimeToNext);
                        MINECRAFT.getMusicManager().stopPlaying(); //kills vanilla music
                    } else {
                        timeToNext--;
                    }
                }
            }
        }
    }

    public static void addTrack(SoundEvent event, int priority, int minDelay, int maxDelay) {
        if (priority > currentPriority) {
            shouldPlayTrack = SimpleSound.forMusic(event);
            currentPriority = priority;
            lowestTimeToNext = minDelay;
            highestTimeToNext = maxDelay;
        }
    }

    private static void switchTracks() {
        MINECRAFT.getSoundManager().stop(currentTrack);
        currentTrack = shouldPlayTrack;
        if (currentTrack == null) {
            BackgroundMusicSelector vanillaMusic = MINECRAFT.getSituationalMusic();
            MINECRAFT.getMusicManager().nextSongDelay = (MathHelper.nextInt(RANDOM, 0, vanillaMusic.getMinDelay() / 2)); //recreates a vanilla music swap
        }
    }

    public static boolean isMusicPlaying(SoundEvent event) {
        return (currentTrack != null && event.getLocation() == currentTrack.getLocation() && MINECRAFT.getSoundManager().isActive(currentTrack));
    }
}