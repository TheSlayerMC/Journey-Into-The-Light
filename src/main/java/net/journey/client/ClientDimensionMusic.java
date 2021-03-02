package net.journey.client;

import net.journey.JITL;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientDimensionMusic {
	private static final Minecraft MC = Minecraft.getMinecraft();
	private static final MusicTicker MUSIC_TICKER = new MusicTicker(MC);

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END) {
			if (type.equals(TickEvent.Type.CLIENT)) {
				if (!MC.isGamePaused() && MC.world != null) {
					MUSIC_TICKER.update();
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onMusicControl(PlaySoundEvent event) {
		ISound sound = event.getSound();
		SoundCategory category = sound.getCategory();
		if (category == SoundCategory.MUSIC) {
			if (MC.currentScreen != null) {
				if (MC.player != null) {
					if (MC.player.dimension == Config.euca ||
							MC.player.dimension == Config.depths ||
							MC.player.dimension == Config.boil ||
							MC.player.dimension == Config.frozen ||
							MC.player.dimension == Config.corba ||
							MC.player.dimension == Config.cloudia) {
						if (!sound.getSoundLocation().getNamespace().equals(JITL.MOD_ID) && (MUSIC_TICKER.isPlayingMusic() || !MUSIC_TICKER.isPlayingMusic())) {
							event.setResultSound(null);
						}
					}
				}
			}
		} else if (category == SoundCategory.RECORDS) {
			MUSIC_TICKER.stopMusic();
			MC.getSoundHandler().stopSounds();

		}
	}


}