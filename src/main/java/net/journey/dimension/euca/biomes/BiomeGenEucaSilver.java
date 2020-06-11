package net.journey.dimension.euca.biomes;

import net.journey.dimension.base.BiomeGenJourney;
import net.journey.dimension.euca.biomes.properties.BiomePropertiesEucaSilver;
import net.journey.dimension.euca.gen.trees.WorldGenEucaSilverTree;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaTree;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
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

        this.flower = new WorldGenModFlower(JourneyBlocks.eucaSilverGoldFlower, JourneyBlocks.eucaSilverGrass);
        this.bush = new WorldGenModFlower(JourneyBlocks.eucaSilverTallGrass, JourneyBlocks.eucaSilverGrass);

        this.decorator.treesPerChunk = 10;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
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
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        int i1;
        int j1;
        int k1;
        int l1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        if (TerrainGen.decorate(worldIn, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            for (i1 = 0; i1 < 7; ++i1) {
                j1 = rand.nextInt(16) + 8;
                k1 = rand.nextInt(16) + 8;
                l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
                new WorldGenModFlower(JourneyBlocks.eucaSilverGrass, JourneyBlocks.eucaSilverTallGrass).generate(worldIn, rand, pos.add(j1, l1, k1));
            }
        }
        super.decorate(worldIn, rand, pos);
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