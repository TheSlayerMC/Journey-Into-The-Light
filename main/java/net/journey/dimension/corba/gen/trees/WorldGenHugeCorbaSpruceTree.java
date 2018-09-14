package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WorldGenHugeCorbaSpruceTree extends WorldGenCorbaHugeTree
{
	private boolean field_150542_e;
	private Block log = JourneyBlocks.corbaLog;
	Block leaves = JourneyBlocks.corbaLeaves;
	
	public WorldGenHugeCorbaSpruceTree(boolean par1, boolean par2)
	{
		super(par1, 8, 20);
		this.field_150542_e = par2;
	}

	public boolean generate(World w, Random rand, BlockPos pos) {

		int posX = pos.getX(), posY = pos.getY(), posZ = pos.getZ();

		int l = this.setHeight(rand);

		if (!this.gen(w, rand, posX, posY, posZ, l))
		{
			return false;
		}
		else
		{
			this.func_150541_c(w, posX, posZ, posY + l, 0, rand);

			for (int i1 = 0; i1 < l; ++i1)
			{
				Block block = w.getBlockState(new BlockPos(posX, posY + i1, posZ)).getBlock();

				if (block.isAir(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX, posY + i1, posZ)) || block.isLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX, posY + i1, posZ)))
				{
					this.setBlockAndNotifyAdequately(w, new BlockPos(posX, posY + i1, posZ), log.getDefaultState());
					//this.setBlockAndNotifyAdequately(w, new BlockPos(posX, posY + i1, posZ), log.getDefaultState());
				}

				if (i1 < l - 1)
				{
					block = w.getBlockState(new BlockPos(posX + 1, posY + i1, posZ)).getBlock();

					if (block.isAir(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX + 1, posY + i1, posZ)) || block.isLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX + 1, posY + i1, posZ)))
					{
						this.setBlockAndNotifyAdequately(w, new BlockPos(posX + 1, posY + i1, posZ), log.getDefaultState());
					}

					block = w.getBlockState(new BlockPos(posX + 1, posY + i1, posZ + 1)).getBlock();

					if (block.isAir(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX + 1, posY + i1, posZ + 1)) || block.isLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX + 1, posY + i1, posZ + 1)))
					{
						this.setBlockAndNotifyAdequately(w, new BlockPos(posX + 1, posY + i1, posZ + 1), log.getDefaultState());
					}

					block = w.getBlockState(new BlockPos(posX, posY + i1, posZ + 1)).getBlock();

					if (block.isAir(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX, posY + i1, posZ + 1)) || block.isLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(posX, posY + i1, posZ + 1)))
					{
						this.setBlockAndNotifyAdequately(w, new BlockPos(posX, posY + i1, posZ + 1), log.getDefaultState());
					}
				}
			}

			return true;
		}
	}

	private void func_150541_c(World w, int par2_, int par3_, int par4_, int par5_, Random par6_) {
		int i1 = par6_.nextInt(5);
		if (this.field_150542_e) {
			i1 += this.baseHeight;
		}
		else {
			i1 += 3;
		}
		int j1 = 0;
		for (int k1 = par4_ - i1; k1 <= par4_; ++k1) {
			int l1 = par4_ - k1;
			int i2 = par5_ + MathHelper.floor((float)l1 / (float)i1 * 3.5F);
			this.grow3(w, par2_, k1, par3_, i2 + (l1 > 0 && i2 == j1 && (k1 & 1) == 0 ? 1 : 0), par6_);
			j1 = i2;
		}
	}

	public void findBlockPosition(World w, Random par2_, int par3_, int par4_, int par5_) {
		this.checkBlock(w, par2_, par3_ - 1, par4_, par5_ - 1);
		this.checkBlock(w, par2_, par3_ + 2, par4_, par5_ - 1);
		this.checkBlock(w, par2_, par3_ - 1, par4_, par5_ + 2);
		this.checkBlock(w, par2_, par3_ + 2, par4_, par5_ + 2);

		for (int l = 0; l < 5; ++l) {
			int i1 = par2_.nextInt(64);
			int j1 = i1 % 8;
			int k1 = i1 / 8;

			if (j1 == 0 || j1 == 7 || k1 == 0 || k1 == 7) {
				this.checkBlock(w, par2_, par3_ - 3 + j1, par4_, par5_ - 3 + k1);
			}
		}
	}

	private void checkBlock(World par1_, Random par2_, int par3_, int par4_, int par5_) {
		for (int l = -2; l <= 2; ++l) {
			for (int i1 = -2; i1 <= 2; ++i1) {
				if (Math.abs(l) != 2 || Math.abs(i1) != 2) {
					this.sustainBlock(par1_, par3_ + l, par4_, par5_ + i1);
				}
			}
		}
	}

	private void sustainBlock(World w, int par2_, int par3_, int par4_) {
		for (int l = par3_ + 2; l >= par3_ - 3; --l) {
			Block block = w.getBlockState(new BlockPos(par2_, l, par4_)).getBlock();
			if (block.canSustainPlant(w.getBlockState(new BlockPos(par2_, par3_, par4_)), w, new BlockPos(par2_, l, par4_), EnumFacing.UP, (BlockSapling)Blocks.SAPLING)) {
				this.setBlockAndNotifyAdequately(w, new BlockPos(par2_, l, par4_), JourneyBlocks.corbaStone.getDefaultState());
				break;
			}
			if (block.isAir(w.getBlockState(new BlockPos(par2_, l, par4_)), w, new BlockPos(par2_, l, par4_)) && l < par3_) {
				break;
			}
		}
	}
}