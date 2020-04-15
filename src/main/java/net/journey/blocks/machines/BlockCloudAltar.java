package net.journey.blocks.machines;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityCloudAltar;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockCloudAltar extends BlockModContainer {

	private AxisAlignedBB size = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 2.8F, 1.0F);
	
	public BlockCloudAltar(String name, String finalName) {
		super(name, finalName);
		setCreativeTab(JourneyTabs.machineBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCloudAltar();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return size;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return size;
	}

	public class CloudAltarTEISR extends TileEntityItemStackRenderer {
		
		private final ModelCloudAltar model = new ModelCloudAltar();
		
		@Override
		public void renderByItem(ItemStack itemStackIn) {
			if(itemStackIn.getItem() == SlayerAPI.toItem(JourneyBlocks.cloudAltar)) {
				this.model.render(1.0F, true);
			}
		}
	}
}