package net.jitl.common.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class FrozenCaveCarver extends CaveWorldCarver {

    public FrozenCaveCarver(Codec<ProbabilityConfig> c) {
        super(c, 64);
        this.replaceableBlocks = ImmutableSet.of(JBlocks.GRASSY_PERMAFROST, JBlocks.PERMAFROST, JBlocks.CRUMBLED_PERMAFROST);
        this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
    }

    @Override
    protected int getCaveBound() {
        return 15;
    }

    @Override
    protected float getThickness(Random rand) {
        float f = rand.nextFloat() * 4.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 5.0F + 2.0F;
        }
        return f;
    }

    @Override
    protected double getYScale() {
        return 1.0D;
    }

    @Override
    protected int getCaveY(Random r) {
        return r.nextInt(this.genHeight);
    }

    @Override
    protected boolean carveBlock(IChunk chunkIn, Function<BlockPos, Biome> function_, BitSet carvingMask, Random rand, BlockPos.Mutable mutable_, BlockPos.Mutable mutable1_, BlockPos.Mutable mutable2_, int int3_, int int4_, int int_, int posX, int posZ, int j, int posY, int k, MutableBoolean isSurface) {
        int i = j | k << 4 | posY << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            mutable_.set(posX, posY, posZ);
            if (this.canReplaceBlock(chunkIn.getBlockState(mutable_))) {
                BlockState blockstate;
                if (posY <= 10) {
                    blockstate = LAVA.createLegacyBlock(); //TODO: replace with some kind of molten ice or something?
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