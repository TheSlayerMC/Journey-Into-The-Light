package net.journey.blocks;

import net.journey.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockChangeable extends BlockMod {
	
	public Item key;
	public Block blockChangedInto;
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockChangeable(String name, String f, Item key, Block blockchangedinto) {
		super(EnumMaterialTypes.METAL_SOUND, name, f, 0.5F);
		this.key = key;
		this.blockChangedInto = blockchangedinto;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setTickRandomly(true);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(playerIn.getHeldItemMainhand() !=null && playerIn.getHeldItemMainhand().getItem() == key) {
			JourneySounds.playSound(JourneySounds.UNLOCK, worldIn, playerIn);
			JourneySounds.playSound(JourneySounds.GATE_CREAK, worldIn, playerIn);
			worldIn.setBlockState(pos.add(0, 0, 0), blockChangedInto.getDefaultState());
			return true;
		}
		return false;
	}
}