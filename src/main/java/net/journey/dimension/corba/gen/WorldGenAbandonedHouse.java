package net.journey.dimension.corba.gen;

import net.journey.JITL;
import net.journey.api.block.GroundPredicate;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModStairs;

import java.util.Random;

public class WorldGenAbandonedHouse extends WorldGenerator {

	protected GroundPredicate groundPredicate = GroundPredicate.CORBA_SWAMP;

	public WorldGenAbandonedHouse() {
	}

	public IBlockState getRandomBrickStates(Random rand) {
		return RandHelper.chooseEqual(rand,
				JourneyBlocks.corbaBricks.getDefaultState(),
				JourneyBlocks.corbaCrackedBricks.getDefaultState(),
				JourneyBlocks.corbaDarkBricks.getDefaultState(),
				JourneyBlocks.corbaLightBricks.getDefaultState(),
				JourneyBlocks.corbaBrickStairs.getDefaultState().withProperty(BlockModStairs.FACING, getRandomFacing(rand)),
				Blocks.AIR.getDefaultState());
	}

	public IBlockState getRandomChestStates(Random rand, EnumFacing facing) {
		if (rand.nextInt(5) == 0)
			return RandHelper.chooseEqual(rand, Blocks.AIR.getDefaultState(), Blocks.WEB.getDefaultState());
		else return JourneyBlocks.corbaChest.getDefaultState().withProperty(BlockModStairs.FACING, facing);
	}

	public IBlockState getRandomStairStates(Random rand, EnumFacing facing) {
		if (rand.nextInt(5) == 0)
			return RandHelper.chooseEqual(rand, Blocks.AIR.getDefaultState(), Blocks.WEB.getDefaultState());
		else return JourneyBlocks.corbaBrickStairs.getDefaultState().withProperty(BlockModStairs.FACING, facing);
	}

	public IBlockState getRandomPlankStates(Random rand) {
		if (rand.nextInt(5) == 0)
			return RandHelper.chooseEqual(rand, Blocks.AIR.getDefaultState(), Blocks.WEB.getDefaultState());
		else return JourneyBlocks.corbaPlank.getDefaultState();
	}

	public IBlockState getRandomGlassStates(Random rand) {
		if (rand.nextInt(5) == 0)
			return RandHelper.chooseEqual(rand, Blocks.AIR.getDefaultState(), Blocks.WEB.getDefaultState());
		else return Blocks.GLASS.getDefaultState();
	}

	private EnumFacing getRandomFacing(Random rand) {
		return RandHelper.chooseEqual(rand, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH);
	}

	private void setTileEntityLootTable(World world, Random random, BlockPos pos) {
		TileEntity chest = world.getTileEntity(pos);
		if (chest instanceof TileEntityJourneyChest) {
			((TileEntityJourneyChest) chest).setLootTable(getRandomLootTable(random), random.nextLong());
		}
	}

	private ResourceLocation getRandomLootTable(Random random) {
		return RandHelper.chooseEqual(random, JourneyLootTables.LOOT_BASIC, JourneyLootTables.LOOT_GOLD, JourneyLootTables.LOOT_DIAMOND, JourneyLootTables.LOOT_OVERGROWN);
	}

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		BlockPos soilPos = blockPos.down();
		IBlockState soilState = world.getBlockState(soilPos);
		boolean isSoil = groundPredicate.testGround(world, soilPos, soilState, EnumFacing.UP);
		if (isSoil) {
			JITL.LOGGER.info("hellos");
			int k = 0;
			int i = 0;
			int j = 0;
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 0), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 1), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 7), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 0, j + 4, k + 8), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 0, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 1, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 2), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 3), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 5), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 6), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 2, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 2), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 3), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 5), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 6), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 3, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 4, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 0), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 1), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 7), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 1, j + 5, k + 8), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 1), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 0, k + 7), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 1, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 1, k + 5), getRandomChestStates(random, EnumFacing.SOUTH));

			setTileEntityLootTable(world, random, soilPos.add(i + 2, j + 1, k + 5));

			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 1, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 2, k + 1), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 2, k + 7), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 3, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 3, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 4, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 4, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 2), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 3), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 4), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 5), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 6), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 5, k + 7), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 0), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 1), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 7), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 2, j + 6, k + 8), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 1), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 0, k + 7), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 1, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 1, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 2, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 2, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 3, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 3, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 4, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 4, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 2), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 3), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 5), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 6), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 5, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 6, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 0), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 1), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 7), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 3, j + 7, k + 8), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 1), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 4), JourneyBlocks.stinkySpawner.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 0, k + 7), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 1, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 2, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 3, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 3, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 4, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 4, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 5, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 5, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 6, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 6, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 2), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 3), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 4), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 5), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 6), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 7, k + 7), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 0), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 1), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 2), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 3), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 4), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 5), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 6), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 7), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 4, j + 8, k + 8), JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 1), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 0, k + 7), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 1, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 1, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 2, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 2, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 3, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 3, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 4, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 4, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 2), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 3), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 5), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 6), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 5, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 6, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 0), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 1), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 7), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 5, j + 7, k + 8), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 1), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 0, k + 7), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 1, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 1, k + 2), getRandomChestStates(random, EnumFacing.WEST));

			this.setTileEntityLootTable(world, random, soilPos.add(i + 6, j + 1, k + 2));

			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 1, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 2, k + 1), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 2, k + 7), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 3, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 3, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 4, k + 1), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 4, k + 7), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 2), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 3), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 4), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 5), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 6), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 5, k + 7), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 0), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 1), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 7), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 6, j + 6, k + 8), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 2), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 3), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 4), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 5), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 6), getRandomPlankStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 0, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 1, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 2), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 3), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 5), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 6), getRandomGlassStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 2, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 1), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 2), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 3), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 4), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 5), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 6), getRandomBrickStates(random));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 3, k + 7), JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 1), getRandomStairStates(random, EnumFacing.SOUTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 2), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 3), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 4), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 5), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 6), getRandomStairStates(random, EnumFacing.EAST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 4, k + 7), getRandomStairStates(random, EnumFacing.NORTH));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 0), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 1), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 7), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 7, j + 5, k + 8), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 0), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 1), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 2), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 3), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 4), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 5), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 6), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 7), getRandomStairStates(random, EnumFacing.WEST));
			this.setBlockAndNotifyAdequately(world, soilPos.add(i + 8, j + 4, k + 8), getRandomStairStates(random, EnumFacing.WEST));
		}
		return true;
	}
}
