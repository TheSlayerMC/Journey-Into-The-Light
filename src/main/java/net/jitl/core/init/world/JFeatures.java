package net.jitl.core.init.world;

import net.jitl.common.world.gen.features.CaveVinesFeature;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.jitl.common.world.gen.features.boil.*;
import net.jitl.common.world.gen.features.euca.BoulderFeature;
import net.jitl.common.world.gen.features.euca.EucaBotSpawner;
import net.jitl.common.world.gen.features.euca.EucaTreeFeature;
import net.jitl.common.world.gen.features.euca.GlimmerRootFeature;
import net.jitl.common.world.gen.features.featureconfig.*;
import net.jitl.common.world.gen.features.frozen.FrostyIceClusterFeature;
import net.jitl.common.world.gen.features.frozen.FrozenIceSpikeFeature;
import net.jitl.common.world.gen.features.frozen.IcicleFeature;
import net.jitl.common.world.gen.features.frozen.LargeIcicleFeature;
import net.jitl.core.JITL;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JFeatures {
    @AutoRegistrable
    private static final SimpleForgeRegister<Feature<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.FEATURES, JITL.MODID);

    public static final RegistryObject<Feature<RuinsFeatureConfig>> RUINS = REGISTER.register("ruins", () -> new RuinsFeature(RuinsFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CAVE_VINES = REGISTER.register("cave_vines", () -> new CaveVinesFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<EucaTreeFeatureConfig>> EUCA_TREE = REGISTER.register("euca_tree", () -> new EucaTreeFeature(EucaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FROZEN_ICE_SPIKE = REGISTER.register("frozen_ice_spike", () -> new FrozenIceSpikeFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GLIMMER_ROOTS = REGISTER.register("glimmer_roots", () -> new GlimmerRootFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> EUCA_BOT_SPAWNER = REGISTER.register("euca_bot_spawner", () -> new EucaBotSpawner(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<SpringConfiguration>> EUCA_WATER_GEN = REGISTER.register("euca_water_gen", () -> new SpringFeature(SpringConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCORCHED_STALAGMITE = REGISTER.register("scorched_stalagmite", () -> new ScorchedStalagmiteFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<BlockStateConfiguration>> SULPHUR_DEPOSIT = REGISTER.register("sulphur_deposit", () -> new SulphurDepositFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SULPHUR_CRYSTAL = REGISTER.register("sulphur_crystal", () -> new SulphurCrystalFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> TORRID_CRYSTAL = REGISTER.register("torrid_crystal", () -> new TorridCrystalFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VOLCANIC_ROCK = REGISTER.register("volcanic_rock", () -> new VolcaniocRockFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<BlockStateConfiguration>> BOULDER = REGISTER.register("boulder", () -> new BoulderFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<IcicleFeatureConfig>> FROZEN_ICICLE = REGISTER.register("frozen_icicle", () -> new IcicleFeature(IcicleFeatureConfig.CODEC));
    public static final RegistryObject<Feature<FrostyIceClusterFeatureConfig>> FROSTY_ICE_CLUSTER = REGISTER.register("frosty_ice_cluster", () -> new FrostyIceClusterFeature(FrostyIceClusterFeatureConfig.CODEC));
    public static final RegistryObject<Feature<LargeIcicleFeatureConfig>> LARGE_ICICLE = REGISTER.register("large_icicle", () -> new LargeIcicleFeature(LargeIcicleFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLAME_BULB = REGISTER.register("flame_bulb", () -> new FlameBulbFeature(NoneFeatureConfiguration.CODEC));
}
