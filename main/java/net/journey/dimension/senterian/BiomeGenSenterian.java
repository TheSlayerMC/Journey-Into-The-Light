package net.journey.dimension.senterian;

import net.journey.JourneyBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenSenterian extends BiomeGenBase {

	public BiomeGenSenterian(int par1) {
		super(par1);
		this.setBiomeName("Senterian Labyrinth");
		this.topBlock = JourneyBlocks.senterianFloor.getDefaultState();
		this.fillerBlock = JourneyBlocks.senterianBricks.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        setColor(0x5b592d);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0x5b592d;
    }
}