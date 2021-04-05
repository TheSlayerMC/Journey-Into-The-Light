package net.jitl.common.capability.essence;

import net.minecraft.entity.player.ServerPlayerEntity;

public interface IEssenceCapability {
    void setEssence(ServerPlayerEntity player, float value);

    void addEssence(ServerPlayerEntity player, float value);

    float getEssenceValue();

    boolean consumeEssence(ServerPlayerEntity player, float value);
}
