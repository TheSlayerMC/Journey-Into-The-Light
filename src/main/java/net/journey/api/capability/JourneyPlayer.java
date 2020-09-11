package net.journey.api.capability;

import net.journey.util.exception.WrongSideException;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;

public interface JourneyPlayer {
    EssenceStorage getEssenceStorage();

    PlayerStats getPlayerStats();

    void onTick(Side side);

    /**
     * Universal time before teleporting for all portals
     */
    int timeBeforeTeleport();

    /**
     * Call whenever a player collides with portal block
     */
    void setInPortal(Block portalBlock);

    boolean isInPortal();

    /**
     * Syncs all capability data with client.
     * Should be called only on server.
     *
     * @throws WrongSideException if called on client.
     */
    void sendUpdates();
}
