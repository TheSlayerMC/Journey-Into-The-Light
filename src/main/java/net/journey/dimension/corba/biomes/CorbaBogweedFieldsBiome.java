package net.journey.dimension.corba.biomes;

import net.journey.dimension.base.biome.EnumBiomeColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CorbaBogweedFieldsBiome extends CorbaSwampBiome {

	public CorbaBogweedFieldsBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return EnumBiomeColor.CORBA_FOREST.getInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return EnumBiomeColor.CORBA_FOREST.getInt();
	}
}
