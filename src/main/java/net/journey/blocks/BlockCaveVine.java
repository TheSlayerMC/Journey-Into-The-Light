package net.journey.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import net.slayer.api.block.BlockModVine;

public class BlockCaveVine extends BlockMod implements IPlantable, IGrowable {

    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);
    
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
	
	public BlockCaveVine(String name, String f) {
		super(EnumMaterialTypes.PLANT, name, f, 2);
		setLightLevel(0.3F);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BUSH_AABB;
    }

	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}
	
	private boolean canBlockStay(World world, BlockPos pos) {
		return canPlaceBelow(world, pos.up());
	}

	public static boolean canPlaceBelow(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block blockAbove = state.getBlock();

		return state.getMaterial() == Material.ROCK || state.getMaterial() == Material.GROUND || state.getMaterial() == Material.PLANTS;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		this.checkAndDropBlock(world, pos);
		
		int size;

		for (size = 1; world.getBlockState(pos.up(size)).getBlock() == this; ++size) {
			;
		}
		boolean canGrow = (rand.nextInt(5) == 0);

		if (ForgeHooks.onCropsGrowPre(world, pos, state, canGrow)) {
			int age = state.getValue(AGE).intValue();
			if (age < 3) {
				world.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(age + 1)), 2);
			}
			if (rand.nextInt(3) == 0 && size < 3 && world.getBlockState(pos.up()).getBlock() == Blocks.AIR
					&& age >= 2) {
				world.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, Integer.valueOf(0)), 2);
			}
			ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
		}
	}

	@Override
	@Deprecated
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		this.checkAndDropBlock(world, pos);
	}
	
	private void checkAndDropBlock(World world, BlockPos pos) {
		if (!this.canBlockStay(world, pos)) {
			world.destroyBlock(pos, true);
		}
	}
	
	@Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) { return true; }
    
	@Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos){return false; }
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)  {
		return 1000;
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return null;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return this.getDefaultState();
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int age = state.getValue(AGE).intValue();
		IBlockState bottom = worldIn.getBlockState(pos.down());
		
        if (age < 2) {
            int setMeta = rand.nextInt(2) + 1 + age;
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(setMeta)), 4);
            return;
        }
        
		if (bottom == null || worldIn.isAirBlock(pos.down())) {
            if (rand.nextInt(1) == 0) {
                worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(AGE, Integer.valueOf(0)), 2);
            }
            return;
		}
	}
}