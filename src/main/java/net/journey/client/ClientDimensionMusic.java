package net.journey.client;

import net.journey.util.Config;
import net.journey.util.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientDimensionMusic {

	private Minecraft mc = Minecraft.getMinecraft();
	private MusicTicker musicTicker = new MusicTicker(mc);

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws Exception {
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END) {
			if (type.equals(TickEvent.Type.CLIENT)) {
				if (!mc.isGamePaused()) {
					musicTicker.update();
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onMusicControl(PlaySoundEvent event) {
		ISound sound = event.getSound();
		SoundCategory category = sound.getCategory();
		if(category == SoundCategory.MUSIC) {
			if(mc.currentScreen != null) {
				if(this.mc.player != null) {
					musicTicker.setDimensionID(mc.player.dimension);
					if(this.mc.player.dimension == Config.euca || this.mc.player.dimension == Config.depths || this.mc.player.dimension == Config.corba || this.mc.player.dimension == Config.cloudia) {
						if (!sound.getSoundLocation().toString().contains("journey") && (this.musicTicker.playingMusic() || !this.musicTicker.playingMusic())) {
							event.setResultSound(null);
							return;
						}
					}
				}
			}
		}
		else if (category == SoundCategory.RECORDS) {
			this.musicTicker.stopMusic();
			this.mc.getSoundHandler().stopSounds();

			return;
		}
	}


}