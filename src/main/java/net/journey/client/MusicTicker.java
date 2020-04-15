package net.journey.client;

import net.journey.JourneySounds;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class MusicTicker {

    private final Random rand = new Random();
    private final Minecraft mc;
    private ISound currentMusic;
    private int timeUntilNextMusic = 100;
    private int id;

    public MusicTicker(Minecraft mcIn) {
        this.mc = mcIn;
    }

    public void update() {
        TrackType tracktype = this.getRandomTrack();

        if (this.mc.player != null) {
            if (this.mc.player.dimension != getDimensionID()) {
                this.stopMusic();
            } else if (this.mc.player.dimension == getDimensionID()) {
                if (this.currentMusic != null) {
                    if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
                        this.currentMusic = null;
                        this.timeUntilNextMusic = Math.min(MathHelper.getInt(this.rand, tracktype.getMinDelay(), tracktype.getMaxDelay()), this.timeUntilNextMusic);
                    }
                }
                this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, tracktype.getMaxDelay());
                if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
                    this.playMusic(tracktype);
                }
            }
        }
    }

    public int getDimensionID() {
        return id;
    }

    public void setDimensionID(int id) {
        this.id = id;
    }

    public boolean playingMusic() {
        return this.currentMusic != null;
    }

    public MusicTicker.TrackType getRandomTrack() {
        TrackType type = TrackType.EMPTY;
        int dimID = getDimensionID();
        if (dimID == Config.depths) {
            int tracks = rand.nextInt(2);
            return tracks == 0 ? TrackType.DEPTHS_1 : tracks == 1 ? TrackType.DEPTHS_2 : TrackType.DEPTHS_1;
        }

        if (dimID == Config.euca) {
            int tracks = rand.nextInt(3);
            return tracks == 0 ? TrackType.EUCA_1 : tracks == 1 ? TrackType.EUCA_2 : tracks == 1 ? TrackType.EUCA_3 : TrackType.EUCA_1;
        }

        if (dimID == Config.corba) {
            int tracks = rand.nextInt(1);
            return tracks == 0 ? TrackType.CORBA_1 : TrackType.CORBA_1;
        }

        if (dimID == Config.terrania) {
            int tracks = rand.nextInt(1);
            return tracks == 0 ? TrackType.TERRANIA_1 : TrackType.TERRANIA_1;
        }

        if (dimID == Config.boil) {
            int tracks = rand.nextInt(1);
            return tracks == 0 ? TrackType.BOIL_1 : TrackType.BOIL_1;
        }

        if (dimID == Config.cloudia) {
            int tracks = rand.nextInt(2);
            return tracks == 0 ? TrackType.CLOUDIA_1 : tracks == 1 ? TrackType.CLOUDIA_2 : TrackType.CLOUDIA_1;
        }

        if (dimID == Config.frozen) {
            int tracks = rand.nextInt(1);
            return tracks == 0 ? TrackType.FROZEN_1 : TrackType.FROZEN_1;
        }

        //return num == 0 ? TrackType.TRACK_ONE : num == 1 ? TrackType.TRACK_TWO : num == 2 ? TrackType.TRACK_THREE : TrackType.TRACK_FOUR;
        return type;
    }

    public void playMusic(TrackType requestedMusicType) {
        this.currentMusic = PositionedSoundRecord.getMusicRecord(requestedMusicType.getMusicLocation());
        this.mc.getSoundHandler().playSound(this.currentMusic);
        this.timeUntilNextMusic = Integer.MAX_VALUE;
    }

    public void stopMusic() {
        if (this.currentMusic != null) {
            this.mc.getSoundHandler().stopSound(this.currentMusic);
            this.currentMusic = null;
            this.timeUntilNextMusic = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    public enum TrackType {
        EMPTY(JourneySounds.EMPTY, 300, 700),
        EUCA_1(JourneySounds.EUCA_1, 1200, 1500),
        EUCA_2(JourneySounds.EUCA_2, 620, 900),
        EUCA_3(JourneySounds.EUCA_3, 3300, 3600),
        DEPTHS_1(JourneySounds.DEPTHS_1, 2400, 2700),
        DEPTHS_2(JourneySounds.DEPTHS_2, 4300, 4600),
        CORBA_1(JourneySounds.CORBA_1, 5100, 5400),
        CLOUDIA_1(JourneySounds.CLOUDIA_1, 1000, 1200),
        CLOUDIA_2(JourneySounds.CLOUDIA_2, 2500, 2800),
        TERRANIA_1(JourneySounds.TERRANIA_1, 3300, 3600),
        BOIL_1(JourneySounds.BOIL_1, 3140, 3440),
        FROZEN_1(JourneySounds.FROZEN_1, 3500, 3800);

        private final SoundEvent musicLocation;
        private final int minDelay;
        private final int maxDelay;

        TrackType(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn) {
            this.musicLocation = musicLocationIn;
            this.minDelay = minDelayIn;
            this.maxDelay = maxDelayIn;
        }

        public SoundEvent getMusicLocation() {
            return this.musicLocation;
        }

        public int getMinDelay() {
            return this.minDelay;
        }

        public int getMaxDelay() {
            return this.maxDelay;
        }
    }
}