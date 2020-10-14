package net.journey.dimension.base.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JDimensionBiome extends Biome {

	protected BiomeFactory<Biome> biomeFactory;

	public JDimensionBiome(BiomeProperties properties) {
		super(properties);

		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
	}

	public JDimensionBiome(Biome.BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		this(properties);
		this.topBlock = topBlock;
		this.fillerBlock = fillerBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return getGrassColorAtPos(pos);
	}

	public JDimensionBiome isRareBiome(boolean isRare) {
		biomeFactory.create(this, isRare);
		return this;
	}

	public interface BiomeFactory<T extends Biome> {
		T create(Biome biome, boolean isRare);
	}
}
