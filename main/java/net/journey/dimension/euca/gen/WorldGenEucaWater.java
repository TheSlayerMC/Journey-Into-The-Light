package net.journey.dimension.euca.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEucaWater extends WorldGenerator {
	
	private final Block block;
	private final boolean update;

	public WorldGenEucaWater(Block block, boolean update) {
		this.block = block;
		this.update = update;
	}

	@Override
	public boolean generate(World w, Random r, BlockPos p) {
		Block genOn = JourneyBlocks.eucaStone;
		if (w.getBlockState(p.up()).getBlock() != genOn) {
			return false;
		}
		else if (w.getBlockState(p).getBlock().getMaterial(null) != Material.AIR && w.getBlockState(p).getBlock() != genOn) {
			return false;
		} else {
			int i = 0;
			if(w.getBlockState(p.west()).getBlock() == genOn) ++i;          
			if(w.getBlockState(p.east()).getBlock() == genOn) ++i;         
			if(w.getBlockState(p.north()).getBlock() == genOn) ++i;           
			if(w.getBlockState(p.south()).getBlock() == genOn) ++i;           
			if(w.getBlockState(p.down()).getBlock() == genOn) ++i;            
			int j = 0;
			if(w.isAirBlock(p.west())) ++j;           
			if(w.isAirBlock(p.east())) ++j;           
			if(w.isAirBlock(p.north())) ++j;
			if(w.isAirBlock(p.south())) ++j;            
			if(w.isAirBlock(p.down())) ++j;           
			if(!this.update && i == 4 && j == 1 || i == 5) {
                IBlockState iblockstate = this.block.getDefaultState();
				w.setBlockState(p, iblockstate, 2);
				w.immediateBlockTick(p, iblockstate, r);
			}
			return true;
		}
	}
}