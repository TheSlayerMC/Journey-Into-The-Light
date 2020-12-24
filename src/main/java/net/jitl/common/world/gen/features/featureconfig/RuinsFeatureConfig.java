package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class RuinsFeatureConfig implements IFeatureConfig {

	public static final Codec<RuinsFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
			instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((ruinsFeatureConfig -> ruinsFeatureConfig.spawnBlock)),
					WeightedBlockStateProvider.CODEC.fieldOf("ruined_blocks_provider").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.ruinedBlocksProvider),
					Codec.INT.fieldOf("max_spreading").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.maxSpreading),
					Codec.INT.fieldOf("max_height").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.maxHeight)
			).apply(instance, RuinsFeatureConfig::new));

	public final RuleTest spawnBlock;
	public final WeightedBlockStateProvider ruinedBlocksProvider;
	public int maxSpreading;
	public int maxHeight;

	public RuinsFeatureConfig(RuleTest spawnBlock, WeightedBlockStateProvider ruinedBlocksProvider, int maxSpreading, int maxHeight) {
		this.spawnBlock = spawnBlock;
		this.ruinedBlocksProvider = ruinedBlocksProvider;
		this.maxSpreading = maxSpreading;
		this.maxHeight = maxHeight;
	}
}
