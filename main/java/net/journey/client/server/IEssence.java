package net.journey.client.server;

import net.minecraft.entity.player.EntityPlayer;

public interface IEssence {

    void useEssence(EntityPlayer player, int points);
    void fill(EntityPlayer player, int points);
    void regen(EntityPlayer player);
    void set(int points);
    int getEssence();
    int getMaxEssence();
    void setMaxEssence(int max);
    int getRegenDelay();
    void setRegenDelay(int delay);
}