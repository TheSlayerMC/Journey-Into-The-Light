package net.jitl.common.capability.player;

import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.capability.player.data.FogDensity;
import net.jitl.common.capability.player.data.Sentacoins;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public interface IJPlayer extends INBTSerializable<CompoundTag> {

    Player getPlayer();

    static LazyOptional<IJPlayer> get(Player player) {
        return player.getCapability(JCapabilityProvider.PLAYER);
    }

    Essence getEssence();
    Sentacoins getSentacoins();
    FogDensity fogDensity();
}