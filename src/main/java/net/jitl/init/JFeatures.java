package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.features.CaveVinesFeature;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JFeatures {
    @AutoRegistrable
    private static final SimpleForgeRegister<Feature<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.FEATURES, JITL.MODID);

    public static final RegistryObject<Feature<RuinsFeatureConfig>> RUINS = REGISTER.register("ruins", () -> new RuinsFeature(RuinsFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> CAVE_VINES = REGISTER.register("cave_vines", () -> new CaveVinesFeature(NoFeatureConfig.CODEC));
}
