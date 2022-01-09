package net.jitl.common.item.gear;

import net.minecraft.world.entity.Entity;

public interface ILightGear {
    default double scaleWithDarkness(Entity entity, double original) {
        return original * (1 - entity.getBrightness());
    }
}
