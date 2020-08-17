package net.journey.client.server;

public interface IEssence {

    boolean useEssence(int points);

    void addEssence(int points);

    int getEssenceValue();

    int getMaxValue();

    boolean isFull();

    void update();

    void regen();
}