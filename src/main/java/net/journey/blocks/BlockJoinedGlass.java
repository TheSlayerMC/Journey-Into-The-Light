package net.journey.blocks;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockJoinedGlass extends BlockMod {

	private String[] textures = new String[16];
	//private IIcon[] icons = new IIcon[16];

	public BlockJoinedGlass(String name, String f) {
		super(EnumMaterialTypes.GLASS, name, f, 0.5F);
        textures[0] = SlayerAPI.PREFIX + "glass/" + name + "/glass";
        textures[1] = SlayerAPI.PREFIX + "glass/" + name + "/glass_1_d";
        textures[2] = SlayerAPI.PREFIX + "glass/" + name + "/glass_1_u";
        textures[3] = SlayerAPI.PREFIX + "glass/" + name + "/glass_1_l";
        textures[4] = SlayerAPI.PREFIX + "glass/" + name + "/glass_1_r";
        textures[5] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_h";
        textures[6] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_v";
        textures[7] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_dl";
        textures[8] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_dr";
        textures[9] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_ul";
        textures[10] = SlayerAPI.PREFIX + "glass/" + name + "/glass_2_ur";
        textures[11] = SlayerAPI.PREFIX + "glass/" + name + "/glass_3_d";
        textures[12] = SlayerAPI.PREFIX + "glass/" + name + "/glass_3_u";
        textures[13] = SlayerAPI.PREFIX + "glass/" + name + "/glass_3_l";
        textures[14] = SlayerAPI.PREFIX + "glass/" + name + "/glass_3_r";
        textures[15] = SlayerAPI.PREFIX + "glass/" + name + "/glass_4";
	}
	

	public boolean shouldConnectTo(IBlockAccess w, int x, int y, int z, Block b) {
		return b == this;
	}

	/*private IIcon getConnectedTexture(IBlockAccess w, int x, int y, int z, int side) {
		boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

		switch(side) {
		case 0:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x - 1, y, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x + 1, y, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z - 1))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z + 1))) isOpenRight = true;
			
			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[11];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[12];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[13];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[14];
			else if(isOpenDown && isOpenUp) return icons[5];
			else if(isOpenLeft && isOpenRight) return icons[6];
			else if(isOpenDown && isOpenLeft) return icons[8];
			else if(isOpenDown && isOpenRight) return icons[10];
			else if(isOpenUp && isOpenLeft) return icons[7];
			else if(isOpenUp && isOpenRight) return icons[9];
			else if(isOpenDown) return icons[3];
			else if(isOpenUp) return icons[4];
			else if(isOpenLeft) return icons[2];
			else if(isOpenRight) return icons[1];
			break;
		case 1:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x - 1, y, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x + 1, y, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z - 1))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z + 1))) isOpenRight = true;
			
			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[11];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[12];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[13];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[14];
			else if(isOpenDown && isOpenUp) return icons[5];
			else if(isOpenLeft && isOpenRight) return icons[6];
			else if(isOpenDown && isOpenLeft) return icons[8];
			else if(isOpenDown && isOpenRight) return icons[10];
			else if(isOpenUp && isOpenLeft) return icons[7];
			else if(isOpenUp && isOpenRight) return icons[9];
			else if(isOpenDown) return icons[3];
			else if(isOpenUp) return icons[4];
			else if(isOpenLeft) return icons[2];
			else if(isOpenRight) return icons[1];
			break;
		case 2:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y - 1, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y + 1, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x - 1, y, z))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x + 1, y, z))) isOpenRight = true;
			
			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[13];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[14];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[11];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[12];
			else if(isOpenDown && isOpenUp) return icons[6];
			else if(isOpenLeft && isOpenRight) return icons[5];
			else if(isOpenDown && isOpenLeft) return icons[9];
			else if(isOpenDown && isOpenRight) return icons[10];
			else if(isOpenUp && isOpenLeft) return icons[7];
			else if(isOpenUp && isOpenRight) return icons[8];
			else if(isOpenDown) return icons[1];
			else if(isOpenUp) return icons[2];
			else if(isOpenLeft) return icons[4];
			else if(isOpenRight) return icons[3];
			break;
		case 3:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y - 1, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y + 1, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x - 1, y, z))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x + 1, y, z))) isOpenRight = true;
			
			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[14];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[13];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[11];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[12];
			else if(isOpenDown && isOpenUp) return icons[6];
			else if(isOpenLeft && isOpenRight) return icons[5];
			else if(isOpenDown && isOpenLeft) return icons[10];
			else if(isOpenDown && isOpenRight) return icons[9];
			else if(isOpenUp && isOpenLeft) return icons[8];
			else if(isOpenUp && isOpenRight) return icons[7];
			else if(isOpenDown) return icons[1];
			else if(isOpenUp) return icons[2];
			else if(isOpenLeft) return icons[3];
			else if(isOpenRight) return icons[4];
			break;
		case 4:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y - 1, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y + 1, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z - 1))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z + 1))) isOpenRight = true;

			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[14];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[13];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[11];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[12];
			else if(isOpenDown && isOpenUp) return icons[6];
			else if(isOpenLeft && isOpenRight) return icons[5];
			else if(isOpenDown && isOpenLeft) return icons[10];
			else if(isOpenDown && isOpenRight) return icons[9];
			else if(isOpenUp && isOpenLeft) return icons[8];
			else if(isOpenUp && isOpenRight) return icons[7];
			else if(isOpenDown) return icons[1];
			else if(isOpenUp) return icons[2];
			else if(isOpenLeft) return icons[3];
			else if(isOpenRight) return icons[4];
			
			break;
		case 5:
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y - 1, z))) isOpenDown = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y + 1, z))) isOpenUp = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z - 1))) isOpenLeft = true;
			if(shouldConnectTo(w, x, y, z, w.getBlock(x, y, z + 1))) isOpenRight = true;
			
			if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight) return icons[15];
			else if(isOpenUp && isOpenDown && isOpenLeft) return icons[13];
			else if(isOpenUp && isOpenDown && isOpenRight) return icons[14];
			else if(isOpenUp && isOpenLeft && isOpenRight) return icons[11];
			else if(isOpenDown && isOpenLeft && isOpenRight) return icons[12];
			else if(isOpenDown && isOpenUp) return icons[6];
			else if(isOpenLeft && isOpenRight) return icons[5];
			else if(isOpenDown && isOpenLeft) return icons[9];
			else if(isOpenDown && isOpenRight) return icons[10];
			else if(isOpenUp && isOpenLeft) return icons[7];
			else if(isOpenUp && isOpenRight) return icons[8];
			else if(isOpenDown) return icons[1];
			else if(isOpenUp) return icons[2];
			else if(isOpenLeft) return icons[4];
			else if(isOpenRight) return icons[3];
			break;
		}
		return icons[0];
	}

	

	/*@Override
	public void registerBlockIcons(IIconRegister r) {
		for(int i = 0; i < textures.length; i++) {
			icons[i] = r.registerIcon(textures[i]);
		}
	}*/
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState par1, Random par2, int par3) {
		return null;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	/*@Override
	public IIcon getIcon(int s, int m) {
		return icons[0];
	}*/
    
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if(this == JourneyBlocks.hotGlass || this == JourneyBlocks.frozenGlass || this == JourneyBlocks.smoothGlass) {
            if(blockState != iblockstate) {
                return true;
            }
            if(block == this) {
                return false;
            }
        }
        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}


	/*@Override
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int s) {
		return getConnectedTexture(w, x, y, z, s);
	}*/
}