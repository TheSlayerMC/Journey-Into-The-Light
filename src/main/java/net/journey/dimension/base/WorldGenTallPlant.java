package net.journey.dimension.base;

import net.journey.blocks.base.JBlockDoublePlant;
import net.journey.util.MathUtils;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenTallPlant extends WorldGenerator {

	JBlockDoublePlant plant;
	Block ground;
	
	public WorldGenTallPlant(World worldIn, Random r, BlockPos b, JBlockDoublePlant plant, Block ground) {
		this.ground = ground;
		this.plant = plant;
	}
	
    @Override
    public boolean generate(World worldIn, Random r, BlockPos b) {
        boolean generated = false;
        BlockPos offset = WorldGenAPI.createRandom(b.getX(), 0, worldIn.getHeight(), b.getZ(), r, 10);
    	
        int coercedY = MathUtils.coerceInRange(WorldGenAPI.findPosAboveSurface(worldIn, offset).getY(), 1, 151);
        int genY = r.nextInt(coercedY) + 1;
        BlockPos copy = offset.add(r.nextInt(8) - r.nextInt(8), r.nextInt(4) - r.nextInt(4), r.nextInt(8) - r.nextInt(8));

	    JBlockDoublePlant block = plant;

        if (!worldIn.getBlockState(offset).getMaterial().isLiquid()
                && worldIn.getBlockState(offset.down()).getBlock() == ground
                && block.canPlaceBlockAt(worldIn, offset)) {

            block.placeAt(worldIn, offset, 2 | 16);
            generated = true;
        }

        return generated;
    }
}