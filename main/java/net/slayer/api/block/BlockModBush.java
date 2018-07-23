package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

public class BlockModBush extends BlockMod implements IGrowable, IPlantable {

	private boolean isNether;
	private ItemStack berry;
	
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);

	public BlockModBush(String name, String finalName, ItemStack berry, boolean isNether) {
		super(EnumMaterialTypes.LEAVES, name, finalName, 1.0F);
		this.berry = berry;
		this.isNether = isNether;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setLightOpacity(-100000);
		this.setCreativeTab(JourneyTabs.crops);
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
		float f = 0.3F;
		if (access.getBlockState(pos).getValue(AGE) == 0) {
			return new AxisAlignedBB(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 1.0F, 0.5F + f);
		}

		if (access.getBlockState(pos).getValue(AGE) == 1) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (access.getBlockState(pos).getValue(AGE) == 2) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		return null;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean canPlaceBlockAt(World w, BlockPos pos) {
		Block block = w.getBlockState(pos.down()).getBlock();
		if(isNether) {
			return block == Blocks.NETHERRACK;
		}
		if(!isNether) {
			return block == Blocks.GRASS;
		}
		return false;
	}

	@Override
	public void updateTick(World w, BlockPos pos, IBlockState state, Random rand) {
		if (w.rand.nextInt(5) == 0) {
			int age = state.getValue(AGE).intValue();
			if (age < 2) {
				w.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
			}		            
		}
	}

	@Override
	public IBlockState getStateForPlacement(World w, BlockPos pos, EnumFacing face, float x, float y, float z, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(0));

	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf((meta & 15) >> 2));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(AGE).intValue() << 2;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {AGE});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return state.getValue(AGE).intValue() < 2;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(state.getValue(AGE).intValue() + 1)), 2);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) {
            return this.getDefaultState();
        }
        return state;
    }

	public boolean canBlockStay(World world, BlockPos pos1) {
		return this.canPlaceBlockAt(world, pos1);
	}
	
	public void setDrop(ItemStack itemIn) {
        this.berry = itemIn;
    }
    
    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (state.getValue(AGE) == 2) {
			if (w.isRemote) { 
				return true;
			}
            w.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(0)), 1);
            ItemStack itemDrop = new ItemStack(this.berry.getItem(), 1, this.berry.getItemDamage());
            EntityItem entityitem = new EntityItem(w, player.posX, player.posY - 1.0D, player.posZ, itemDrop);
            w.spawnEntity(entityitem);
            if (!(player instanceof FakePlayer)) {
                entityitem.onCollideWithPlayer(player);
            }
			return true;
		}
		return false;
	}

}
