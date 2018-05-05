package net.journey.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockJoinedPane extends BlockMod {

	private String[] textures = new String[16];
	//private IIcon[] icons = new IIcon[16];

	public BlockJoinedPane(String name, String f) {
		super(EnumMaterialTypes.GLASS, name + "Pane", f, 0.2F);
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
	
	/*@Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        boolean flag  = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ - 1, NORTH);
        boolean flag1 = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ + 1, SOUTH);
        boolean flag2 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ - 1, p_149743_3_, p_149743_4_, WEST );
        boolean flag3 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ + 1, p_149743_3_, p_149743_4_, EAST );

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
            }
            else if (!flag2 && flag3) {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
            }
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
            }
            else if (!flag && flag1) {
                this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
            }
        } else {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }
    }
	
	@Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag  = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ - 1, NORTH);
        boolean flag1 = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ + 1, SOUTH);
        boolean flag2 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ - 1, p_149719_3_, p_149719_4_, WEST );
        boolean flag3 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ + 1, p_149719_3_, p_149719_4_, EAST );

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                f = 0.0F;
            }
            else if (!flag2 && flag3) {
                f1 = 1.0F;
            }
        } else {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                f2 = 0.0F;
            }
            else if (!flag && flag1) {
                f3 = 1.0F;
            }
        } else {
            f2 = 0.0F;
            f3 = 1.0F;
        }
        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }
    
    public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
        return canPaneConnectToBlock(world.getBlock(x, y, z)) || world.isSideSolid(x, y, z, dir.getOpposite(), false);
    }
    
    public final boolean canPaneConnectToBlock(Block b) {
        return b.func_149730_j() || b == this || b == Blocks.glass || b == Blocks.stained_glass || b == Blocks.stained_glass_pane || b instanceof BlockPane || b instanceof BlockJoinedGlass || b instanceof BlockJoinedPane;
    }

	public boolean shouldConnectTo(IBlockAccess w, int x, int y, int z, Block b) {
		return b == this;
	}

	private IIcon getConnectedTexture(IBlockAccess w, int x, int y, int z, int side) {
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
	}*/

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
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
    public boolean shouldSideBeRendered (IBlockAccess w, int x, int y, int z, int s) {
        return w.getBlock(x, y, z) == this ? false : super.shouldSideBeRendered(w, x, y, z, s);
    }*/
}