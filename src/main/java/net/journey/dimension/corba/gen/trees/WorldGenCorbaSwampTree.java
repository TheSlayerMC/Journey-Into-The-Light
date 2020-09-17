package net.journey.dimension.corba.gen.trees;

import net.journey.api.block.GroundPredicate;
import net.journey.blocks.base.JBlockFungalShelf;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenCorbaSwampTree extends WorldGenAbstractTree {

    protected GroundPredicate groundPredicate = GroundPredicate.CORBA_SWAMP;

    private final int treeHeight = 5;

    public WorldGenCorbaSwampTree() {
        super(false);
    }

    public WorldGenCorbaSwampTree(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos blockPos) {
        int treeHeight = random.nextInt(2) + random.nextInt(2) + this.treeHeight;

        BlockPos soilPos = blockPos.down();
        IBlockState soilState = world.getBlockState(soilPos);
        boolean isSoil = groundPredicate.testGround(world, soilPos, soilState, EnumFacing.UP);

        BlockPos.MutableBlockPos basePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, blockPos)).move(EnumFacing.DOWN);
        BlockPos.MutableBlockPos logPos = basePos;

        if (isSoil) {

            BlockPos.MutableBlockPos stumpPos = basePos;

            int stumpHeight = random.nextInt(5) + 2;

            for (int i = 0; i < stumpHeight; i++) {
                placeStumps(world, stumpPos);
            }

            for (int i = 0; i < treeHeight; i++) {
                placeLog(world, logPos);
            }

            BlockPos.MutableBlockPos leafPos = logPos.move(EnumFacing.DOWN, 2);

            for (int j5 = 0; j5 <= 10; ++j5) {
                for (int l5 = 0; l5 <= 10; ++l5) {
                    createCrown(world, leafPos.offset(getRandomDirection(random), random.nextInt(2)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.offset(getRandomDirection(random), random.nextInt(5)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.offset(getRandomDirection(random), random.nextInt(3)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.offset(getRandomDirection(random), random.nextInt(5)), random.nextInt(3) + 1);
                }
            }

            for (int i = 0; i < random.nextInt(5) + 2; i++) {
                placeStumps(world, stumpPos);
            }

            BlockPos.MutableBlockPos stemPos1 = leafPos;

            int stemChance = random.nextInt(2);
            switch (stemChance) {
                case 0:
                    for (int j5 = 0; j5 <= 1; ++j5) {
                        for (int l5 = 0; l5 <= 1; ++l5) {
                            placeStems(world, stemPos1, EnumFacing.EAST);
                        }
                    }
                case 1:
                    for (int j5 = 0; j5 <= 1; ++j5) {
                        for (int l5 = 0; l5 <= 1; ++l5) {
                            placeStems(world, stemPos1, EnumFacing.WEST);
                        }
                    }
                case 2:
            }

            for (int i = 0; i < random.nextInt(3); i++) {
                placeMushroom(world, blockPos.north(2).up(random.nextInt(stumpHeight - 1) + 1), EnumFacing.NORTH);
                placeMushroom(world, blockPos.north(1).up(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), EnumFacing.NORTH);

                if (stemChance != 1) {
                    placeMushroom(world, blockPos.west(2).up(random.nextInt(stumpHeight - 1) + 1), EnumFacing.WEST);
                    placeMushroom(world, blockPos.west(1).up(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), EnumFacing.WEST);
                }

                if (stemChance != 0) {
                    placeMushroom(world, blockPos.east(2).up(random.nextInt(stumpHeight - 1) + 1), EnumFacing.EAST);
                    placeMushroom(world, blockPos.east(1).up(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), EnumFacing.EAST);
                }

                placeMushroom(world, blockPos.south(2).up(random.nextInt(stumpHeight - 1) + 1), EnumFacing.SOUTH);
                placeMushroom(world, blockPos.south(1).up(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), EnumFacing.SOUTH);
            }
        }
        return true;
    }

    private EnumFacing getRandomDirection(Random random) {
        return RandHelper.chooseEqual(random, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.UP);
    }

    private void placeLog(World world, BlockPos.MutableBlockPos pos) {
        setBlockAndNotifyAdequately(world, pos.move(EnumFacing.UP), JourneyBlocks.bogwoodLog.getDefaultState());
    }

    private void placeStumps(World world, BlockPos.MutableBlockPos logPos) {
        setBlockAndNotifyAdequately(world, logPos.move(EnumFacing.UP).offset(EnumFacing.EAST), JourneyBlocks.bogwoodLog.getDefaultState());
        setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST), JourneyBlocks.bogwoodLog.getDefaultState());
        setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.NORTH), JourneyBlocks.bogwoodLog.getDefaultState());
        setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.SOUTH), JourneyBlocks.bogwoodLog.getDefaultState());
    }

    private void placeStems(World world, BlockPos.MutableBlockPos logPos, EnumFacing direction) {
        setBlockAndNotifyAdequately(world, logPos.move(direction, 1).move(EnumFacing.UP, 1).offset(EnumFacing.DOWN, 5), JourneyBlocks.bogwoodLog.getDefaultState());
    }

    private void createCrown(World world, BlockPos pos, int size) {
        pos = pos.offset(EnumFacing.UP, 2);
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {

                    int distance;

                    if (x >= y & x >= z) distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);

                    else if (y >= x & y >= z) distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);

                    else distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);

                    if (distance <= size) {
                        placeLeaves(world, pos.add(-x, +y, +z));
                        placeLeaves(world, pos.add(-x, +y, -z));
                        placeLeaves(world, pos.add(+x, +y, +z));
                        placeLeaves(world, pos.add(+x, +y, -z));
                    }
                }
            }
        }
    }

    private void placeLeaves(World world, BlockPos pos) {
        setBlockAndNotifyAdequately(world, pos, JourneyBlocks.bogwoodLeaves.getDefaultState());
    }

    private void placeMushroom(World world, BlockPos pos, EnumFacing facing) {
        setBlockAndNotifyAdequately(world, pos, JourneyBlocks.swamp_shelf.getDefaultState().withProperty(JBlockFungalShelf.FACING, facing));
    }
}
