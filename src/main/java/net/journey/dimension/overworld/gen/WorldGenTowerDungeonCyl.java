package net.journey.dimension.overworld.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.journey.util.WorldGenHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTowerDungeonCyl extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int levels = 4;
		int height = levels * 4;

		WorldGenHelper.genHollowCylinder(position, 6, 4, EnumFacing.UP, mutablePos -> {
			IBlockState state = JourneyBlocks.dungeonBricks.getDefaultState();

			if (rand.nextInt(15) == 0) {
				state = JourneyBlocks.dungeonChiseledBricks.getDefaultState();
			}
			if (rand.nextInt(10) == 0) {
				state = RandHelper.chooseEqual(rand, JourneyBlocks.dungeonCarvedBricks, JourneyBlocks.dungeonCrackedBricks)
						.getDefaultState();
			}

			WorldGenHelper.setStateFast(worldIn, mutablePos, state);
		});

		return true;
	}

	public void addLevel(World w, int x, int y, int z, int height, int lor, String mobName, String mobName2) {

	}
}