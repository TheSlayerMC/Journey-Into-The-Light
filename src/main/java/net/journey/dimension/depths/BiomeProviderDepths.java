package net.journey.dimension.depths;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderDepths extends BiomeProviderSingle {

    public BiomeProviderDepths() {
        super(DimensionHelper.DEPTHS_BIOME);
    }
}