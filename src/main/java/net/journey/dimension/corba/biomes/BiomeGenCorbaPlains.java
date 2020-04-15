package net.journey.dimension.corba.biomes;

import net.journey.JourneyBlocks;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.corba.BiomePropertiesCorba;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCorbaPlains extends Biome {

	public BiomeGenCorbaPlains() {
		super(new BiomePropertiesCorba());
		this.topBlock = JourneyBlocks.corbaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0x5b592d;
    }
}