package net.journey.util;

import java.util.Random;

public class RandUtils {
    /**
     * Chooses with equal probability.
     */
    @SafeVarargs
    public static <T> T chooseEqual(Random r, T... items) {
        return items[r.nextInt(items.length)];
    }
}
