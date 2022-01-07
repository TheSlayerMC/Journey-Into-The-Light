package net.jitl.common.helper;

import net.minecraft.sounds.SoundEvent;

public class JMusic {
    private final SoundEvent event;
    private final int priority;
    private final int minDuration;
    private final int maxDuration;

    public JMusic(SoundEvent music, int musicPriority, int min, int max) {
        event = music;
        priority = musicPriority;
        minDuration = min;
        maxDuration = max;
    }

    public SoundEvent getEvent() {
        return event;
    }

    public int getMusicImportance() {
        return priority;
    }

    public int getMin() {
        return minDuration;
    }

    public int getMax() {
        return maxDuration;
    }
}
