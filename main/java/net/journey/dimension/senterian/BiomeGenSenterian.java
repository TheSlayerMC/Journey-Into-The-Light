package net.journey.dimension.senterian;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenSenterian extends Biome {

	public BiomeGenSenterian() {
		super(new BiomeProperties("Senterian Labyrinth"));
		this.topBlock = JourneyBlocks.senterianFloor.getDefaultState();
		this.fillerBlock = JourneyBlocks.senterianBricks.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		return 0x5b592d;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0x5b592d;
    }
}