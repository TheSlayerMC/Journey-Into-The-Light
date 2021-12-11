package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.features.CaveVinesFeature;
import net.jitl.common.world.gen.features.JTreeFeature;
import net.jitl.common.world.gen.features.RuinsFeature;
import net.jitl.common.world.gen.features.boil.*;
import net.jitl.common.world.gen.features.euca.EucaBotSpawner;
import net.jitl.common.world.gen.features.euca.EucaTreeFeature;
import net.jitl.common.world.gen.features.euca.GlimmerRootFeature;
import net.jitl.common.world.gen.features.featureconfig.EucaSpawnerFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.EucaTreeFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.JBaseTreeFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.jitl.common.world.gen.features.frozen.FrozenIceSpikeFeature;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
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
    public static final RegistryObject<Feature<EucaTreeFeatureConfig>> EUCA_TREE = REGISTER.register("euca_tree", () -> new EucaTreeFeature(EucaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> FROZEN_ICE_SPIKE = REGISTER.register("frozen_ice_spike", () -> new FrozenIceSpikeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<JBaseTreeFeatureConfig>> BASE_TREE = REGISTER.register("base_tree", () -> new JTreeFeature(JBaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> GLIMMER_ROOTS = REGISTER.register("glimmer_roots", () -> new GlimmerRootFeature(NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<EucaSpawnerFeatureConfig>> EUCA_BOT_SPAWNER = REGISTER.register("euca_bot_spawner", () -> new EucaBotSpawner(EucaSpawnerFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> SCORCHED_STALAGMITE = REGISTER.register("scorched_stalagmite", () -> new ScorchedStalagmiteFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SCORCHED_CACTUS = REGISTER.register("scorched_cactus", () -> new ScorchedCactusFeature(NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<BlockStateFeatureConfig>> SULPHUR_DEPOSIT = REGISTER.register("sulphur_deposit", () -> new SulphurDepositFeature(BlockStateFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SULPHUR_CRYSTAL = REGISTER.register("sulphur_crystal", () -> new SulphurCrystalFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> TORRID_CRYSTAL = REGISTER.register("torrid_crystal", () -> new TorridCrystalFeature(BlockStateFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> FLAME_BULB = REGISTER.register("flame_bulb", () -> new FlameBulbFeature(NoFeatureConfig.CODEC));
}
