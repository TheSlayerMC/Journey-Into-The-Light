package net.jitl.common.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;
import java.util.function.Function;

public class FrozenCaveCarver extends CaveWorldCarver {

    public FrozenCaveCarver(Codec<CaveCarverConfiguration> c) {
        super(c);
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
        return 1D;
    }

    @Override
    public boolean carve(CarvingContext context_, CaveCarverConfiguration config_, ChunkAccess chunk_, Function<BlockPos, Biome> biomeAccessor_, Random random_, Aquifer aquifer_, ChunkPos chunkPos_, CarvingMask carvingMask_) {
        int i = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
        int j = random_.nextInt(random_.nextInt(random_.nextInt(this.getCaveBound()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = chunkPos_.getBlockX(random_.nextInt(16));
            double d1 = config_.y.sample(random_, context_);
            double d2 = chunkPos_.getBlockZ(random_.nextInt(16));
            double d3 = config_.horizontalRadiusMultiplier.sample(random_);
            double d4 = config_.verticalRadiusMultiplier.sample(random_);
            double d5 = 5D;//(double)config_.floorLevel.sample(random_);
            WorldCarver.CarveSkipChecker worldcarver$carveskipchecker = (skipContext_, relativeX_, relativeY1_, relativeZ1_, y1_) -> {
                return shouldSkip(relativeX_, relativeY1_, relativeZ1_, d5);//floor level?
            };
            int l = 1;
            if (random_.nextInt(4) == 0) {
                double d6 = config_.yScale.sample(random_);
                float f1 = 1.0F + random_.nextFloat() * 6.0F;
                this.createRoom(context_, config_, chunk_, biomeAccessor_, aquifer_, d0, d1, d2, f1, d6, carvingMask_, worldcarver$carveskipchecker);
                l += random_.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = random_.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (random_.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.getThickness(random_);
                int i1 = i - random_.nextInt(i / 4);
                int j1 = 0;
                this.createTunnel(context_, config_, chunk_, biomeAccessor_, random_.nextLong(), aquifer_, d0, d1, d2, d3, d4, f2, f, f3, 0, i1, this.getYScale(), carvingMask_, worldcarver$carveskipchecker);
            }
        }

        return true;
    }

    private static boolean shouldSkip(double relative_, double relativeY_, double relativeZ_, double minrelativeY_) {
        if (relativeY_ <= minrelativeY_) {
            return true;
        } else {
            return relative_ * relative_ + relativeY_ * relativeY_ + relativeZ_ * relativeZ_ >= 1.0D;
        }
    }
}