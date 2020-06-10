package net.journey.dimension.euca.biomes;

import net.journey.dimension.base.BiomeGenJourney;
import net.journey.dimension.euca.biomes.properties.BiomePropertiesEucaSilver;
import net.journey.dimension.euca.gen.trees.WorldGenEucaSilverTree;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaTree;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.worldgen.WorldGenAPI;

import java.awt.*;
import java.util.Random;

public class BiomeGenEucaSilver extends BiomeGenJourney {

    public BiomeGenEucaSilver() {
        super(new BiomePropertiesEucaSilver());
        this.topBlock = JourneyBlocks.eucaSilverGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.eucaDirt.getDefaultState();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random random) {
        if (random.nextInt(1) == 0) {
            return new WorldGenTerraniaTree(true, 5, 10, JourneyBlocks.eucaGoldLog.getDefaultState(), JourneyBlocks.eucaSilverLeaves.getDefaultState());
        }
        else {
            return TREE_FEATURE;
        }
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos) {
        super.decorate(world, random, pos);
        ChunkPos chunkPos = new ChunkPos(random.nextInt(16), random.nextInt(16));
        for (int times = 0; times < 250; times++) {
            WorldGenAPI.genOnGround(world, chunkPos, random, new WorldGenTerraniaTree(true, 5, 10, JourneyBlocks.terranianLog.getDefaultState(), JourneyBlocks.terraniaLeaves.getDefaultState()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(0.855F, 0.216F, 5.0F).getRGB();
    }

    @Override
    public boolean canRain() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0xff9c00;
    }
}