package net.journey.blocks.machines;

import net.journey.JITL;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.journey.client.GuiHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockKnowledgeTable extends BlockModContainer {

	public BlockKnowledgeTable(String name, String f) {
		super(EnumMaterialTypes.STONE, name, f, 2.0F, JourneyTabs.machineBlocks);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityKnowledgeTable tile = (TileEntityKnowledgeTable)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote) {
			if(tile != null) { 
				playerIn.openGui(JITL.instance, GuiHandler.knowledge, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState meta) {
		return new TileEntityKnowledgeTable();
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityKnowledgeTable();
	}
}