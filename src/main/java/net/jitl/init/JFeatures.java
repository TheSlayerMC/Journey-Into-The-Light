package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class JFeatures<FC extends IFeatureConfig> extends ForgeRegistryEntry<Feature<?>> {

	public static final Feature<NoFeatureConfig> RUINS = register("overworld_ruins", new RuinsFeature(NoFeatureConfig.CODEC));

	private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
		return Registry.register(Registry.FEATURE, JITL.MODID + key, value);
	}
}
