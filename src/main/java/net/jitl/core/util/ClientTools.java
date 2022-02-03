package net.jitl.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;

public class ClientTools {

    public static void playLocalSound(SoundEvent soundEvent, float volume, float pitch) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(soundEvent, volume, pitch));
    }
}
