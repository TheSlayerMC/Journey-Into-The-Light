package net.journey.dimension.golden;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenGoldenGrains extends Biome {

	public BiomeGenGoldenGrains() {
		super(new BiomeProperties("Golden Grains"));
		this.topBlock = JourneyBlocks.goldenGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.goldenStone.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xffef96;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xffef96;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		return 0xffef96;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0xffef96;
    }
}