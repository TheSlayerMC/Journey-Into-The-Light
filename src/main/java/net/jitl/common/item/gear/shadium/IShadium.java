package net.jitl.common.item.gear.shadium;

import net.minecraft.entity.Entity;

public interface IShadium {
    default double scaleWithDarkness(Entity entity, double original) {
        return original * (1 - entity.getBrightness());
    }
}
