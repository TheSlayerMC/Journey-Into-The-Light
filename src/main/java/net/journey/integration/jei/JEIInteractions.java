package net.journey.integration.jei;

import net.minecraftforge.fml.common.Loader;

public final class JEIInteractions {

    static boolean jeiActive = false;

    public static boolean isJEIActive() {
        return jeiActive;
    }

    public static void setJEIActive() {
        if (Loader.isModLoaded("jei"))
            jeiActive = true;
    }
}