package net.jitl.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ClientTools {

    public static void playLocalSound(Supplier<SoundEvent> soundEventSup, float volume, float pitch) {
        playLocalSound(soundEventSup.get(), volume, pitch);
    }

    public static void playLocalSound(SoundEvent soundEvent, float volume, float pitch) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(soundEvent, volume, pitch));
    }
}
