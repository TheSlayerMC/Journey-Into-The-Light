package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.journey.dimension.terrania.gen.shroom.WorldGenTerrashroom;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BlockModMushroom extends BlockModFlower implements IGrowable {

    Class<? extends WorldGenerator> mushroomStructure;

    public BlockModMushroom(String name, String finalName) {
        super(name, finalName);
    }

    public BlockModMushroom(String name, String finalName, Class<? extends WorldGenerator> mushroomStructure) {
        super(name, finalName);
        this.mushroomStructure = mushroomStructure;
    }

    public boolean generateBigMushroom(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.setBlockToAir(pos);
        WorldGenerator worldgenerator = null;
        if (this == JourneyBlocks.terramushroom) {
            switch (rand.nextInt(2)) {
                case 0:
                    worldgenerator = new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPurple);
                    break;
                case 1:
                    worldgenerator = new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPink);
                    break;
            }
        }
        if (worldgenerator != null && worldgenerator.generate(worldIn, rand, pos)) {
            return true;
        } else {
            worldIn.setBlockState(pos, state, 3);
            return false;
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.generateBigMushroom(worldIn, pos, state, rand);
    }
}
