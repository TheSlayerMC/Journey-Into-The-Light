package net.journey.client.server.player;

import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerStats {

    void addSentacoin(int amount);
    
    int getSentacoinValue();

    void update();
    
    void addExperience(EnumPlayerStats stat, int amount, EntityPlayer p);
    
    void addLevel(EnumPlayerStats stat, int amount, EntityPlayer p);

    int getPlayerLevel(EnumPlayerStats stat);
    
    float getPlayerXP(EnumPlayerStats stat);
    
}