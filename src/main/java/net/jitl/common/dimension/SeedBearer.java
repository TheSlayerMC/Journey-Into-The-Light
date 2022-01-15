package net.jitl.common.dimension;

public class SeedBearer {

    private static long seed = 0;

    public static void putSeed(long seedInput) {
        seed = seedInput;
    }

    public static long getSeed() {
        return seed;
    }
}