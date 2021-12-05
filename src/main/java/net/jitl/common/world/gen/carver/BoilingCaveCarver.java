package net.jitl.common.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

import net.jitl.init.JBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class BoilingCaveCarver extends CaveWorldCarver {

    public BoilingCaveCarver(Codec<ProbabilityConfig> codec_) {
        super(codec_, 128);
        this.replaceableBlocks = ImmutableSet.of(JBlocks.VOLCANIC_SAND, JBlocks.CHARRED_GRASS, JBlocks.HOT_GROUND, JBlocks.SCORCHED_RUBBLE, JBlocks.ASH_BLOCK);
        this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
    }

    @Override
    protected int getCaveBound() {
        return 10;
    }

    @Override
    protected float getThickness(Random random_) {
        return (random_.nextFloat() * 2.0F + random_.nextFloat()) * 2.0F;
    }

    @Override
    protected double getYScale() {
        return 5.0D;
    }

    @Override
    protected int getCaveY(Random random_) {
        return random_.nextInt(this.genHeight);
    }

    @Override
    protected boolean carveBlock(IChunk chunkIn, Function<BlockPos, Biome> function_, BitSet carvingMask, Random rand, BlockPos.Mutable mutable_, BlockPos.Mutable mutable1_, BlockPos.Mutable mutable2_, int int3_, int int4_, int int_, int posX, int posZ, int int1_, int posY, int int2_, MutableBoolean isSurface) {
        int i = int1_ | int2_ << 4 | posY << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            mutable_.set(posX, posY, posZ);
            if (this.canReplaceBlock(chunkIn.getBlockState(mutable_))) {
                BlockState blockstate;
                if (posY <= 31) {
                    blockstate = LAVA.createLegacyBlock();
                } else {
                    blockstate = CAVE_AIR;
                }

                chunkIn.setBlockState(mutable_, blockstate, false);
                return true;
            } else {
                return false;
            }
        }
    }
}