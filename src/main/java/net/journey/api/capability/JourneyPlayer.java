package net.journey.api.capability;

import net.minecraftforge.fml.relauncher.Side;

public interface JourneyPlayer {
    EssenceStorage getEssenceStorage();

    PlayerStats getPlayerStats();

    void onTick(Side side);
}
