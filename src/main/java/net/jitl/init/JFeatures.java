package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JFeatures {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

	public static final RegistryObject<Feature<NoFeatureConfig>> RUINS = FEATURES.register(
			"overworld_ruins", () -> new RuinsFeature(NoFeatureConfig.CODEC));

	public static final ConfiguredFeature<?, ?> OVERWORLD_RUINS = RUINS.get().configured(NoFeatureConfig.INSTANCE).chance(1).count(5).squared();
}