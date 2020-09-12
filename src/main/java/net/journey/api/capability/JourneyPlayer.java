package net.journey.api.capability;

import net.journey.util.exception.WrongSideException;
import net.minecraftforge.fml.relauncher.Side;

public interface JourneyPlayer {
    EssenceStorage getEssenceStorage();

    PlayerStats getPlayerStats();

    PlayerOverlay getPlayerOverlay();

    void onTick(Side side);

    /**
     * Syncs all capability data with client.
     * Should be called only on server.
     *
     * @throws WrongSideException if called on client.
     */
    void sendUpdates();
}
