package net.journey.api.capability;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;

public interface JourneyPlayer {
    EssenceStorage getEssenceStorage();

    PlayerStats getPlayerStats();

    void onTick(Side side);

    void sendUpdates(EntityPlayerMP player);
}
