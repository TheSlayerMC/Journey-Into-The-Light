package net.journey.dimension.depths;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderDepths extends BiomeProviderSingle {

    public BiomeProviderDepths() {
        super(DimensionHelper.depths);
    }
}