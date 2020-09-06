package net.journey.api.capability;

import net.journey.common.knowledge.EnumPlayerStats;
import net.minecraft.entity.player.EntityPlayer;

public interface PlayerStats {

    void addSentacoin(int amount);

    int getSentacoinValue();

    void addExperience(EnumPlayerStats type, float amount, EntityPlayer p);

    void addLevel(EnumPlayerStats type, int amount, EntityPlayer p);

    int getPlayerLevel(EnumPlayerStats type);

    float getPlayerXP(EnumPlayerStats type);
}