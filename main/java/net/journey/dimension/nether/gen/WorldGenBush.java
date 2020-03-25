package net.journey.dimension.nether.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModBush;

public class WorldGenBush extends WorldGenerator {
	
	public Block top;
	public Block bush;
	
	public WorldGenBush(World w, Random rand, BlockPos pos, Block bush, Block top) {
		this.top = top;
		this.bush = bush;
		if(bush == JourneyBlocks.juiceberryBush && w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.FOREST || w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.PLAINS) {
			generate(w, rand, pos);
		}
		if(bush == JourneyBlocks.bogberryBush && w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.SWAMPLAND) {
			generate(w, rand, pos);
		}
		if(bush == JourneyBlocks.bradberryBush && w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.TAIGA_HILLS || w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.TAIGA) {
			generate(w, rand, pos);
		}
		if(bush == JourneyBlocks.tangleberryBush && w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.JUNGLE || w.getChunkFromBlockCoords(pos).getBiome(pos, w.getBiomeProvider()) == Biomes.JUNGLE_HILLS) {
			generate(w, rand, pos);
		}
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(
            		rand.nextInt(8) 
            		- rand.nextInt(8), 
            		rand.nextInt(4) 
            		- rand.nextInt(4), 
            		rand.nextInt(8) 
            		- rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && 
            	worldIn.getBlockState(blockpos.down()).getBlock() == 
            	top && 
            	bush.canPlaceBlockAt(worldIn, blockpos)) { worldIn.setBlockState(blockpos, 
                bush.getDefaultState().withProperty(BlockModBush.AGE, 2));
            }
        }
        return true;
	}
}