package net.journey.dimension.corba;

import net.journey.JourneyBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCorba extends BiomeGenBase {

	public BiomeGenCorba(int par1) {
		super(par1);
		this.setBiomeName("Corba");
		this.topBlock = JourneyBlocks.corbaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
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