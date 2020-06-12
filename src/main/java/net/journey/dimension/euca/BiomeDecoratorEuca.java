package net.journey.dimension.euca;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeDecoratorEuca extends BiomeDecorator {

    private WorldGenerator celestiumOre, storonOre, mekyumOre, koriteOre;

    public static WorldGenerator generateOre(World w, Random r, BlockPos pos, Block ore, int vein) {
        Block stone = JourneyBlocks.eucaStone;
        return new WorldGenMinable(ore.getDefaultState(), vein, BlockStateMatcher.forBlock(stone));
    }

    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
        celestiumOre = generateOre(worldIn, random, pos, JourneyBlocks.celestiumOre, 10);
        storonOre = generateOre(worldIn, random, pos, JourneyBlocks.storonOre, 10);
        mekyumOre = generateOre(worldIn, random, pos, JourneyBlocks.mekyumOre, 10);
        koriteOre = generateOre(worldIn, random, pos, JourneyBlocks.koriteOre, 10);

        generateOres(worldIn, random);
    }

    @Override
    protected void generateOres(World worldIn, Random random) {
        genStandardOre1(worldIn, random, 30, celestiumOre, 30, 250);
        genStandardOre1(worldIn, random, 30, storonOre, 30, 250);
        genStandardOre1(worldIn, random, 30, mekyumOre, 30, 250);
        genStandardOre1(worldIn, random, 30, koriteOre, 30, 250);

    }

}