package net.jitl.common.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.Random;
import java.util.function.Function;

public class FrozenCaveCarver extends CaveWorldCarver {
    public FrozenCaveCarver(Codec<CaveCarverConfiguration> codec_) {
        super(codec_);
        this.replaceableBlocks = ImmutableSet.of(JBlocks.PERMAFROST, JBlocks.CRUMBLED_PERMAFROST, JBlocks.GRASSY_PERMAFROST);
        this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
    }

    @Override
    protected int getCaveBound() {
        return 15;
    }

    @Override
    protected float getThickness(Random random_) {
        return (random_.nextFloat() * 3.0F + random_.nextFloat()) * 3.0F;
    }

    @Override
    protected double getYScale() {
        return 2.0D;
    }

    @Override
    protected boolean carveBlock(CarvingContext carvingContext_, CaveCarverConfiguration caveCarverConfiguration_, ChunkAccess chunkAccess_, Function<BlockPos, Biome> function_, CarvingMask carvingMask_, BlockPos.MutableBlockPos mutableBlockPos_, BlockPos.MutableBlockPos mutableBlockPos1_, Aquifer aquifer_, MutableBoolean mutableBoolean_) {
        if (this.canReplaceBlock(chunkAccess_.getBlockState(mutableBlockPos_))) {
            chunkAccess_.setBlockState(mutableBlockPos_, CAVE_AIR, false);
            return true;
        } else {
            return false;
        }
    }
}