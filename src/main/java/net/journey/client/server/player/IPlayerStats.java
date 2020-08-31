package net.journey.client.server.player;

public interface IPlayerStats {

    void addSentacoin(int amount);
    
    int getSentacoinValue();

    void update();
}