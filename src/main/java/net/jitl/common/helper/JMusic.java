package net.jitl.common.helper;

import net.minecraft.util.SoundEvent;

public class JMusic {
    private SoundEvent event;
    private int priority;
    private int minDuration;
    private int maxDuration;

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
