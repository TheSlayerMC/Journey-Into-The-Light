package net.journey.dimension.base.gen;

import com.google.common.primitives.Ints;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModBush;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenBush extends WorldGenerator {
    private static final int SPREADING = 5; // must be less or equal 9 otherwise may cause cascading lags
    private static final int MAX_GROUP = 5;
    private static final int[] AGES = Ints.toArray(BlockModBush.AGE.getAllowedValues());
    public Block acceptableSurface;
    public Block bush;

    public WorldGenBush(Block bush, Block acceptableSurface) {
        this.acceptableSurface = acceptableSurface;
        this.bush = bush;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        pos = WorldGenAPI.optimizeAndRandomize(pos, rand);

        boolean generated = false;

        for (int i = 0; i < rand.nextInt(MAX_GROUP + 1); i++) {
            BlockPos placePos = pos.add(rand.nextInt(SPREADING), 0, rand.nextInt(SPREADING));
            placePos = WorldGenAPI.findPosAboveSurface(worldIn, placePos);

            if (worldIn.getBlockState(placePos.down()).getBlock() == acceptableSurface &&
                    bush.canPlaceBlockAt(worldIn, placePos)) {

                setBlockAndNotifyAdequately(worldIn, placePos, bush.getDefaultState().withProperty(BlockModBush.AGE, AGES[rand.nextInt(AGES.length)]));

                generated = true;
            }
        }

        return generated;
    }
}