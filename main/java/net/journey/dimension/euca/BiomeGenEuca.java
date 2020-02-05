package net.journey.dimension.euca;

import java.awt.Color;

import net.journey.JourneyBlocks;
import net.journey.dimension.BiomeGenJourney;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenEuca extends BiomeGenJourney {

	public BiomeGenEuca() {
		super("Euca");
		
		//this.basicLargeTree = new WorldGenEucaTree();
		//this.basicTree = new WorldGenEucaTree2();
		//this.bush = new WorldGenModFlower(JourneyBlocks.eucaTallGrass, JourneyBlocks.eucaGrass);
		//this.flower = new WorldGenModFlower(JourneyBlocks.eucaTallFlowers, JourneyBlocks.eucaGrass);
		//this.bloom = new WorldGenModFlower(JourneyBlocks.eucaBlueFlower, JourneyBlocks.eucaGrass);
		
		this.topBlock = JourneyBlocks.eucaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.eucaStone.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		
		//this.decorator = new BiomeDecoratorEuca();
		
       // this.flowers.clear();
       // this.decorator.flowersPerChunk = 0;
       // this.decorator.grassPerChunk = 0;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(0.255F, 0.216F, 0.0F).getRGB();
    }
}