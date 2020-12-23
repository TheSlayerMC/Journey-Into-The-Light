package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.SimpleForgeRegister;

public class JFeatures {
	@AutoRegistrable
	private static final SimpleForgeRegister<Feature<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.FEATURES, JITL.MODID);

	public static final RegistryObject<Feature<NoFeatureConfig>> OVERWORLD_RUINS = REGISTER.register("overworld_ruins", () -> new RuinsFeature(NoFeatureConfig.CODEC));
}
