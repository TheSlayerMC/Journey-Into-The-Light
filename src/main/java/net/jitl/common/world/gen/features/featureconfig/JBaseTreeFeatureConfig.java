package net.jitl.common.world.gen.features.featureconfig;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;

public class JBaseTreeFeatureConfig extends TreeConfiguration implements FeatureConfiguration {
    public static final Codec<JBaseTreeFeatureConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((baseTreeFeatureConfig8_) -> {
            return baseTreeFeatureConfig8_.trunkProvider;
        }), BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter((baseTreeFeatureConfig7_) -> {
            return baseTreeFeatureConfig7_.leavesProvider;
        }), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((baseTreeFeatureConfig9_) -> {
            return baseTreeFeatureConfig9_.dirtProvider;
        }), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((baseTreeFeatureConfig6_) -> {
            return baseTreeFeatureConfig6_.foliagePlacer;
        }), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((baseTreeFeatureConfig5_) -> {
            return baseTreeFeatureConfig5_.trunkPlacer;
        }), FeatureSize.CODEC.fieldOf("minimum_size").forGetter((baseTreeFeatureConfig4_) -> {
            return baseTreeFeatureConfig4_.minimumSize;
        }), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((baseTreeFeatureConfig3_) -> {
            return baseTreeFeatureConfig3_.decorators;
        }), Codec.INT.fieldOf("max_water_depth").orElse(0).forGetter((baseTreeFeatureConfig2_) -> {
            return baseTreeFeatureConfig2_.maxWaterDepth;
        }), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((baseTreeFeatureConfig1_) -> {
            return baseTreeFeatureConfig1_.ignoreVines;
        }), Heightmap.Types.CODEC.fieldOf("heightmap").forGetter((baseTreeFeatureConfig_) -> {
            return baseTreeFeatureConfig_.heightmap;
        })).apply(builder, JBaseTreeFeatureConfig::new);
    });
    //TODO: Review this, see if we can hook in the sapling into the Codec
    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider leavesProvider;
    public final BlockStateProvider dirtProvider;
    public final List<TreeDecorator> decorators;
    public transient boolean fromSapling;
    public final FoliagePlacer foliagePlacer;
    public final TrunkPlacer trunkPlacer;
    public final FeatureSize minimumSize;
    public final int maxWaterDepth;
    public final boolean ignoreVines;
    public final Heightmap.Types heightmap;

    protected JBaseTreeFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider dirtProvider, FoliagePlacer foliagePlacer, TrunkPlacer trunkPlacer, FeatureSize minimumSize, List<TreeDecorator> decorators, int maxWaterDepth, boolean ignoreVines, Heightmap.Types type_) {
        super(trunkProvider, leavesProvider, foliagePlacer, trunkPlacer, minimumSize, decorators, maxWaterDepth, ignoreVines, type_);
        this.trunkProvider = trunkProvider;
        this.leavesProvider = leavesProvider;
        this.dirtProvider = dirtProvider;
        this.decorators = decorators;
        this.foliagePlacer = foliagePlacer;
        this.minimumSize = minimumSize;
        this.trunkPlacer = trunkPlacer;
        this.maxWaterDepth = maxWaterDepth;
        this.ignoreVines = ignoreVines;
        this.heightmap = type_;
    }

    public void setFromSapling() {
        this.fromSapling = true;
    }

    public JBaseTreeFeatureConfig withDecorators(List<TreeDecorator> decorators) {
        return new JBaseTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.dirtProvider, this.foliagePlacer, this.trunkPlacer, this.minimumSize, decorators, this.maxWaterDepth, this.ignoreVines, this.heightmap);
    }

    public static class Builder {
        public final BlockStateProvider trunkProvider;
        public final BlockStateProvider leavesProvider;
        public final BlockStateProvider dirtProvider;
        private final FoliagePlacer foliagePlacer;
        private final TrunkPlacer trunkPlacer;
        private final FeatureSize minimumSize;
        private List<TreeDecorator> decorators = ImmutableList.of();
        private int maxWaterDepth;
        private boolean ignoreVines;
        private Heightmap.Types heightmap = Heightmap.Types.OCEAN_FLOOR;

        public Builder(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider dirtProvider, FoliagePlacer foliagePlacer, TrunkPlacer trunkPlacer, FeatureSize minimumSize) {
            this.trunkProvider = trunkProvider;
            this.leavesProvider = leavesProvider;
            this.dirtProvider = dirtProvider;
            this.foliagePlacer = foliagePlacer;
            this.trunkPlacer = trunkPlacer;
            this.minimumSize = minimumSize;
        }

        public Builder decorators(List<TreeDecorator> decorators) {
            this.decorators = decorators;
            return this;
        }

        public Builder maxWaterDepth(int int_) {
            this.maxWaterDepth = int_;
            return this;
        }

        public Builder ignoreVines() {
            this.ignoreVines = true;
            return this;
        }

        public Builder heightmap(Heightmap.Types type_) {
            this.heightmap = type_;
            return this;
        }

        public JBaseTreeFeatureConfig build() {
            return new JBaseTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.dirtProvider, this.foliagePlacer, this.trunkPlacer, this.minimumSize, this.decorators, this.maxWaterDepth, this.ignoreVines, this.heightmap);
        }
    }
}
