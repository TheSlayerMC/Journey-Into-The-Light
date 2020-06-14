package net.journey.dimension.euca.gen;

import net.journey.dimension.base.DimensionHelper;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaSphere extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos p) {
        int size = 6 + r.nextInt(10);
        int s = size / 2;
        
        int x = p.getX() + s;
        int z = p.getZ() + s;
        Biome b = w.getBiome(p);
        
        WorldGenAPI.addOreWorldSphere(w, size, x, p.getY(), z, JourneyBlocks.eucaStone, JourneyBlocks.eucaStone, b == DimensionHelper.EUCA_GOLD_BIOME ? JourneyBlocks.eucaGrass : JourneyBlocks.eucaSilverGrass, 40,
        		JourneyBlocks.celestiumOre, JourneyBlocks.mekyumOre, JourneyBlocks.koriteOre, JourneyBlocks.storonOre);
        
        return true;
    }
}