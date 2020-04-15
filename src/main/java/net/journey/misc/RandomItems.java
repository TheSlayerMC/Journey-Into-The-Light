package net.journey.misc;

import net.journey.JourneyItems;
import net.minecraft.item.Item;

import java.util.Random;

public class RandomItems {

    private Item[] overIngot = new Item[]{JourneyItems.shadiumIngot, JourneyItems.luniumIngot, JourneyItems.sapphire};
    private Item[] eucaIngot = new Item[]{JourneyItems.mekyumIngot, JourneyItems.storonIngot, JourneyItems.koriteIngot, JourneyItems.celestiumIngot};
    private Item[] depthsIngot = new Item[]{JourneyItems.flairiumIngot, JourneyItems.desIngot};
    private Random r = new Random();

    private int random(int i) {
        return r.nextInt(i);
    }

    public Item getItemFromArrayIndex(Item[] item, int index) {
        return item[index];
    }

    public Item getRandomOverworldIngot() {
        return overIngot[random(overIngot.length)];
    }

    public Item getRandomEucaIngot() {
        return eucaIngot[random(eucaIngot.length)];
    }

    public Item getRandomDepthsIngot() {
        return depthsIngot[random(depthsIngot.length)];
    }
}