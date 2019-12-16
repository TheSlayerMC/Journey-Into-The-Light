package net.journey.dimension.euca;

import net.journey.JourneyBlocks;
import net.journey.dimension.BiomeGenJourney;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree2;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

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
		getSkyColorByTemp(0xffd800);
		
		//this.decorator = new BiomeDecoratorEuca();
		
       // this.flowers.clear();
       // this.decorator.flowersPerChunk = 0;
       // this.decorator.grassPerChunk = 0;
	}
}