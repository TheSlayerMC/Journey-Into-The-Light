package net.journey.blocks;

import java.util.Random;

import net.journey.blocks.base.JBlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFrozenPlant extends JBlockFlower {
	
	public BlockFrozenPlant(String name, String enName) {
		super(name, enName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random random) {
		if (random.nextInt(15) == 0) {
			for (int i = 0; i < 6; ++i) {
				double d0 = (double) pos.getX() + random.nextDouble();
				double d1 = (double) pos.getY() + random.nextDouble() * 0.5D + 0.7D;
				double d2 = (double) pos.getZ() + random.nextDouble();
				w.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
		super.randomDisplayTick(stateIn, w, pos, random);
	}
}
