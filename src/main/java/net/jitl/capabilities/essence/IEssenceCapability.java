package net.jitl.capabilities.essence;

import net.minecraft.entity.player.ServerPlayerEntity;

public interface IEssenceCapability {
    void setEssence(ServerPlayerEntity player, float value);

    void addEssence(ServerPlayerEntity player, float value);

    boolean consumeEssence(ServerPlayerEntity player, float value);
}
