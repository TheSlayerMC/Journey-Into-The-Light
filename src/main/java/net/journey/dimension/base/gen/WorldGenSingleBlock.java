package net.journey.dimension.base.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenSingleBlock extends WorldGenerator {

	protected Block block;
	
	public WorldGenSingleBlock(Block block) {
		this.block = block;
	}
	
    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        pos = WorldGenAPI.optimizeAndRandomize(pos, rand);
        pos = WorldGenAPI.getPosWithHeight(pos, rand.nextInt(254 + 1));

        if (pos.getY() >= 110 && rand.nextInt(3) != 0) return false;

        if (world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && block.canPlaceBlockAt(world, pos)) {
            setBlockAndNotifyAdequately(world, pos, block.getDefaultState());
            return true;
        }

        return false;
    }
}
