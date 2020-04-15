package net.journey.blocks;

import net.journey.entity.mob.terrania.mob.EntityTerragrow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockModFlower;

public class BlockTerraFlower extends BlockModFlower {

	public BlockTerraFlower(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		EntityTerragrow grow = new EntityTerragrow(worldIn);
		if(!worldIn.isRemote) {
			worldIn.spawnEntity(grow);
			grow.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}
}
