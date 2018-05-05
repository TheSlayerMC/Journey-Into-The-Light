package net.journey.dimension.golden;

import net.journey.JourneyBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenGoldenGrains extends BiomeGenBase {

	public BiomeGenGoldenGrains(int par1) {
		super(par1);
		this.setBiomeName("Golden Grains");
		this.topBlock = JourneyBlocks.goldenGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.goldenStone.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        setColor(0xffef96);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0xffef96;
    }
}