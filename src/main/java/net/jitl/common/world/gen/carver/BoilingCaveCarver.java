package net.jitl.common.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.jitl.init.world.JCaveCarverConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;
import java.util.function.Function;

public class BoilingCaveCarver extends WorldCarver<JCaveCarverConfiguration> {

    public BoilingCaveCarver(Codec<JCaveCarverConfiguration> c) {
        super(c);
        this.replaceableBlocks = ImmutableSet.of(Blocks.LAVA, JBlocks.VOLCANIC_SAND, JBlocks.CHARRED_GRASS, JBlocks.HOT_GROUND, JBlocks.SCORCHED_RUBBLE, JBlocks.ASH_BLOCK);
        this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
    }

    protected int getCaveBound() {
        return 15;
    }

    protected float getThickness(Random rand) {
        float f = rand.nextFloat() * 6.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        }
        return f;
    }

    @Override
    protected boolean canReplaceBlock(BlockState state_) {
        return this.replaceableBlocks.contains(state_.getBlock());
    }

    protected double getYScale() {
        return 1.0D;
    }

    @Override
    public boolean carve(CarvingContext context, JCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Biome> biomeAccessor, Random random, Aquifer aquifer, ChunkPos chunkPos, CarvingMask carvingMask) {
        int i = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
        int j = random.nextInt(random.nextInt(random.nextInt(this.getCaveBound()) + 1) + 1);
        for(int k = 0; k < j; ++k) {
            double d0 = chunkPos.getBlockX(random.nextInt(16));
            double d1 = config.y.sample(random, context);
            double d2 = chunkPos.getBlockZ(random.nextInt(16));
            double d3 = config.horizontalRadiusMultiplier.sample(random);
            double d4 = config.verticalRadiusMultiplier.sample(random);
            double d5 = config.floorLevel.sample(random);
            WorldCarver.CarveSkipChecker skip = (skipContext, relativeX, relativeY1, relativeZ1, y1) -> shouldSkip(relativeX, relativeY1, relativeZ1, d5);
            int l = 1;
            if (random.nextInt(4) == 0) {
                double d6 = config.yScale.sample(random);
                float f1 = 1.0F + random.nextFloat() * 6.0F;
                this.createRoom(context, config, chunk, biomeAccessor, aquifer, d0, d1, d2, f1, d6, carvingMask, skip);
                l += random.nextInt(4);
            }

            for (int k1 = 0; k1 < l; ++k1) {
                float f = random.nextFloat() * ((float) Math.PI * 2F);
                float f3 = (random.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.getThickness(random);
                int i1 = i - random.nextInt(i / 4);
                this.createTunnel(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, d0, d1, d2, d3, d4, f2, f, f3, 0, i1, this.getYScale(), carvingMask, skip);
            }
        }

        return true;
    }

    @Override
    public boolean isStartChunk(JCaveCarverConfiguration config, Random random) {
        return random.nextFloat() <= config.probability;
    }

    protected void createRoom(CarvingContext context, JCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Biome> biomeAccessor, Aquifer aquifer, double x, double y, double z, float radius, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        double d0 = 1.5D + (Mth.sin(((float) Math.PI / 2F)) * radius);
        double d1 = d0 * horizontalVerticalRatio;
        this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x + 1.0D, y, z, d0, d1, carvingMask, skipChecker);
    }

    protected void createTunnel(CarvingContext context, JCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Biome> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, double horizontalRadiusMultiplier, double verticalRadiusMultiplier, float thickness, float yaw, float pitch, int branchIndex, int branchCount, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        Random random = new Random(seed);
        int i = random.nextInt(branchCount / 2) + branchCount / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for(int j = branchIndex; j < branchCount; ++j) {
            double d0 = 1.5D + (Mth.sin((float) Math.PI * (float) j / (float) branchCount) * thickness);
            double d1 = d0 * horizontalVerticalRatio;
            float f2 = Mth.cos(pitch);
            x += (Mth.cos(yaw) * f2);
            y += Mth.sin(pitch);
            z += (Mth.sin(yaw) * f2);
            pitch *= flag ? 0.92F : 0.7F;
            pitch += f1 * 0.1F;
            yaw += f * 0.1F;
            f1 *= 0.9F;
            f *= 0.75F;
            f1 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && thickness > 1.0F) {
                this.createTunnel(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, random.nextFloat() * 0.5F + 0.5F, yaw - ((float) Math.PI / 2F), pitch / 3.0F, j, branchCount, 1.0D, carvingMask, skipChecker);
                this.createTunnel(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, random.nextFloat() * 0.5F + 0.5F, yaw + ((float) Math.PI / 2F), pitch / 3.0F, j, branchCount, 1.0D, carvingMask, skipChecker);
                return;
            }

            if(random.nextInt(4) != 0) {
                if(!canReach(chunk.getPos(), x, z, j, branchCount, thickness)) {
                    return;
                }
                this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x, y, z, d0 * horizontalRadiusMultiplier, d1 * verticalRadiusMultiplier, carvingMask, skipChecker);
            }
        }
    }

    private static boolean shouldSkip(double relative, double relativeY, double relativeZ, double minrelativeY) {
        if(relativeY <= minrelativeY) {
            return true;
        } else {
            return relative * relative + relativeY * relativeY + relativeZ * relativeZ >= 1.0D;
        }
    }
}