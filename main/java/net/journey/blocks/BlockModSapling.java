package net.journey.blocks;

import java.util.Random;

import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.slayer.api.block.BlockModFlower;

public class BlockModSapling extends BlockModFlower implements IGrowable {

	Class<? extends WorldGenerator> treeStructure;
	
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	
	public BlockModSapling(String name, String f1, Class<? extends WorldGenerator> treeStructure) {
		super(name, f1);
		this.setTickRandomly(true);
		float f = 0.4F;
		this.treeStructure = treeStructure;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }


	@Override
	public void updateTick(World w, BlockPos pos, IBlockState s, Random r)  {
		if(!w.isRemote) {
			super.updateTick(w, pos, s, r);
			if(w.getLightFromNeighbors(pos.up()) >= 9 && r.nextInt(9) == 0)
				this.generate(w, pos, r);
		}
	}

	private void generate(World w, BlockPos pos, Random r) {
		w.setBlockToAir(pos);
		Object tree = treeStructure.getClass();
		((WorldGenerator)tree).generate(w, r, pos);
	}

	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		generate(worldIn, pos, rand);		
	}
}