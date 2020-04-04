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
public class MusicTicker {

	private final Random rand = new Random();
	private final Minecraft mc;
	private ISound currentMusic;
	private int timeUntilNextMusic = 100;
	private int id;
	
	public MusicTicker(Minecraft mcIn)	{
		this.mc = mcIn;
	}

	public void update() {
		TrackType tracktype = this.getRandomTrack();

		if(this.mc.player != null) {
			if(this.mc.player.dimension != getDimensionID()) {
				this.stopMusic();
			}
			else if(this.mc.player.dimension == getDimensionID()) {
				if(this.currentMusic != null) {
					if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
						this.currentMusic = null;
						this.timeUntilNextMusic = Math.min(MathHelper.getInt(this.rand, tracktype.getMinDelay(), tracktype.getMaxDelay()), this.timeUntilNextMusic);
					}
				}
				this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, tracktype.getMaxDelay());
				if(this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
					this.playMusic(tracktype);
				}
			}
		}
	}
	
	public void setDimensionID(int id) {
		this.id = id;
	}
	
	public int getDimensionID() {
		return id;
	}

	public boolean playingMusic() {
		return this.currentMusic != null;
	}

	public MusicTicker.TrackType getRandomTrack() {
		TrackType type = TrackType.EMPTY;
		int dimID = getDimensionID();
		if(dimID == Config.depths) {
			int tracks = rand.nextInt(1);
			return tracks == 0 ? TrackType.DEPTHS_1 : TrackType.DEPTHS_1;
		}
		
		if(dimID == Config.euca) {
			int tracks = rand.nextInt(3);
			return tracks == 0 ? TrackType.EUCA_1 : tracks == 1 ? TrackType.EUCA_2 : tracks == 1 ? TrackType.EUCA_3 : TrackType.EUCA_1;
		}
		
		if(dimID == Config.corba) {
			int tracks = rand.nextInt(1);
			return tracks == 0 ? TrackType.CORBA_1 : TrackType.CORBA_1;
		}
		
		if(dimID == Config.terrania) {
			int tracks = rand.nextInt(1);
			return tracks == 0 ? TrackType.TERRANIA_1 : TrackType.TERRANIA_1;
		}
		
		if(dimID == Config.boil) {
			int tracks = rand.nextInt(1);
			return tracks == 0 ? TrackType.BOIL_1 : TrackType.BOIL_1;
		}
		
		if(dimID == Config.cloudia) {
			int tracks = rand.nextInt(1);
			return tracks == 0 ? TrackType.CLOUDIA_1 : TrackType.CLOUDIA_1;
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
	public static enum TrackType {
		EMPTY(JourneySounds.EMPTY, 300, 700),
		EUCA_1(JourneySounds.EUCA_1, 1200, 1500),
		EUCA_2(JourneySounds.EUCA_2, 1200, 1500),
		EUCA_3(JourneySounds.EUCA_3, 1200, 1500),
		DEPTHS_1(JourneySounds.DEPTHS_1, 1200, 1500),
		CORBA_1(JourneySounds.CORBA_1, 5100, 5400),
		CLOUDIA_1(JourneySounds.CLOUDIA_1, 1200, 1500),
		TERRANIA_1(JourneySounds.TERRANIA_1, 1200, 1500),
		BOIL_1(JourneySounds.BOIL_1, 1200, 1500);

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