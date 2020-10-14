package net.journey.client;

import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.journey.util.RandHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
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

	public DimensionType getDimension() {
		return DimensionType.getById(id);
	}

	public void setDimensionID(int id) {
		this.id = id;
	}

	public boolean isPlayingMusic() {
		return this.currentMusic != null;
	}

	public MusicTicker.TrackType getRandomTrack() {
		TrackType type = TrackType.EMPTY;

		DimensionType dimension = getDimension();

		if (dimension == DimensionHelper.DEPTHS_DIM) {
			return RandHelper.chooseEqual(RandHelper.RANDOM, TrackType.DEPTHS_1, TrackType.DEPTHS_2);
		}
		if (dimension == DimensionHelper.EUCA_DIM) {
			return RandHelper.chooseEqual(RandHelper.RANDOM, TrackType.EUCA_1, TrackType.EUCA_2, TrackType.EUCA_3);
		}
		if (dimension == DimensionHelper.CORBA_DIM) {
			return TrackType.CORBA_1;
		}
		if (dimension == DimensionHelper.TERRANIA_DIM) {
			return TrackType.TERRANIA_1;
		}
		if (dimension == DimensionHelper.BOILING_DIM) {
			return TrackType.BOIL_1;
		}
		if (dimension == DimensionHelper.CLOUDIA_DIM) {
			return RandHelper.chooseEqual(RandHelper.RANDOM, TrackType.CLOUDIA_1, TrackType.CLOUDIA_2);
		}
		if (dimension == DimensionHelper.FROZEN_DIM) {
			return TrackType.FROZEN_1;
		} else {
			return type;
		}
	}

    public void playMusic(TrackType requestedMusicType) {
	    this.currentMusic = PositionedSoundRecord.getMusicRecord(requestedMusicType.getSound());
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

		private final SoundEvent sound;
		private final int minDelay;
		private final int maxDelay;

		TrackType(SoundEvent sound, int minDelayIn, int maxDelayIn) {
			this.sound = sound;
			this.minDelay = minDelayIn;
			this.maxDelay = maxDelayIn;
		}

		public SoundEvent getSound() {
			return this.sound;
		}

		public int getMinDelay() {
			return this.minDelay;
		}

		public int getMaxDelay() {
			return this.maxDelay;
		}
	}
}