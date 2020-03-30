package net.journey.client;

import java.util.Random;

import net.journey.JourneySounds;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MusicTicker implements ITickable {

	private final Random rand = new Random();
	private final Minecraft mc;
	private ISound currentMusic;
	private int timeUntilNextMusic = 100;

	public MusicTicker(Minecraft mcIn)	{
		this.mc = mcIn;
	}

	public void update() {
		TrackType tracktype = this.getRandomTrack();

		if (this.mc.player != null) {
			if (this.mc.player.dimension != Config.euca) {
				this.stopMusic();
			}
			else if (this.mc.player.dimension == Config.euca) {
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

	public boolean playingMusic() {
		return this.currentMusic != null;
	}

	public MusicTicker.TrackType getRandomTrack() {
		int num = this.rand.nextInt(1);

		//return num == 0 ? TrackType.TRACK_ONE : num == 1 ? TrackType.TRACK_TWO : num == 2 ? TrackType.TRACK_THREE : TrackType.TRACK_FOUR;
		return TrackType.TRACK_ONE;
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
	public static enum TrackType {
		TRACK_ONE(JourneySounds.EUCA_1, 1200, 1500);

		private final SoundEvent musicLocation;
		private final int minDelay;
		private final int maxDelay;

		private TrackType(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn) {
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