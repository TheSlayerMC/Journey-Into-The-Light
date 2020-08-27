package net.journey.blocks;

import net.journey.client.render.particles.ParticleTerralight;
import net.journey.util.WorldUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTerraLamp extends BlockSwampLamp {

	public BlockTerraLamp(String name, String finalName, float hardness) {
		super(name, finalName, hardness);
		setLightLevel(0.55F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random rand) {
		float x = pos.getX() + .5F;
		float y = pos.getY() + .2F;
		float z = pos.getZ() + .5F;
		for (int l = 0; l < 6; ++l) {
			WorldUtils.spawnParticle(new ParticleTerralight(w, x, y, z, 1.0F, 1.0F, 1.0F), w);
		}
	}
}
