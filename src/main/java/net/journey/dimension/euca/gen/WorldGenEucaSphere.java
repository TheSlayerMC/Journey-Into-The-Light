package net.journey.dimension.euca.gen;

import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaSphere extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos p) {
        int size = 16 + r.nextInt(15);
        if (!WorldGenAPI.isAirBlocks(w, size + 5, p.getX(), p.getY(), p.getZ()))
            return false;
        WorldGenAPI.addOreWorldSphere(w, size, p.getX(), p.getY(), p.getZ(), JourneyBlocks.eucaStone, JourneyBlocks.eucaStone, JourneyBlocks.eucaGrass, 40, JourneyBlocks.celestiumOre);
        int y = r.nextInt(250);
        int x = p.getX() + r.nextInt(16) + 8;
        int z = p.getZ() + r.nextInt(16) + 8;
        (new WorldGenModFlower(JourneyBlocks.eucaTallGrass, JourneyBlocks.eucaGrass)).generate(w, r, new BlockPos(x, y, z));
        y = r.nextInt(250);
        x = p.getX() + r.nextInt(16) + 8;
        z = p.getZ() + r.nextInt(16) + 8;
        (new WorldGenModFlower(JourneyBlocks.eucaTallFlowers, JourneyBlocks.eucaGrass)).generate(w, r, new BlockPos(x, y, z));
        y = r.nextInt(250);
        x = p.getX() + r.nextInt(16) + 8;
        z = p.getZ() + r.nextInt(16) + 8;
        (new WorldGenModFlower(JourneyBlocks.eucaBlueFlower, JourneyBlocks.eucaGrass)).generate(w, r, new BlockPos(x, y, z));
        return true;
    }
}