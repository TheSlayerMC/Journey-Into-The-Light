package net.journey.blocks.portal;
/*package net.essence.blocks.portal;

import java.util.Random;

import net.essence.EssenceBlocks;
import net.essence.EssenceTabs;
import net.essence.dimension.ModTeleporter;
import net.essence.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWastelandsPortal extends BlockBreakable {

	public static final PropertyEnum field_176550_a = PropertyEnum.create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[] {EnumFacing.Axis.X, EnumFacing.Axis.Z});

	public BlockWastelandsPortal(String name) {
		super(Material.portal, false);
		this.setTickRandomly(true);
		setCreativeTab(EssenceTabs.blocks);
		setUnlocalizedName(name);
		EssenceBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
		EnumFacing.Axis axis = (EnumFacing.Axis)access.getBlockState(pos).getValue(field_176550_a);
		float f = 0.125F;
		float f1 = 0.125F;
		if (axis == EnumFacing.Axis.X) {
			f = 0.5F;
		}
		if (axis == EnumFacing.Axis.Z) {
			f1 = 0.5F;
		}
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		EnumFacing.Axis axis = null;
		IBlockState iblockstate = worldIn.getBlockState(pos);
		if (worldIn.getBlockState(pos).getBlock() == this) {
			axis = (EnumFacing.Axis)iblockstate.getValue(field_176550_a);
			if (axis == null) {
				return false;
			}
			if (axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) {
				return false;
			}
			if (axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
				return false;
			}
		}
		boolean flag = worldIn.getBlockState(pos.west()).getBlock() == this && worldIn.getBlockState(pos.west(2)).getBlock() != this;
		boolean flag1 = worldIn.getBlockState(pos.east()).getBlock() == this && worldIn.getBlockState(pos.east(2)).getBlock() != this;
		boolean flag2 = worldIn.getBlockState(pos.north()).getBlock() == this && worldIn.getBlockState(pos.north(2)).getBlock() != this;
		boolean flag3 = worldIn.getBlockState(pos.south()).getBlock() == this && worldIn.getBlockState(pos.south(2)).getBlock() != this;
		boolean flag4 = flag || flag1 || axis == EnumFacing.Axis.X;
		boolean flag5 = flag2 || flag3 || axis == EnumFacing.Axis.Z;
		return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
			int dimensionID = Config.wastelands;
			Block blockFrame = EssenceBlocks.wastelandsPortalFrame;
			if(thePlayer.timeUntilPortal > 0) 
				thePlayer.timeUntilPortal = 10;
			else if(thePlayer.dimension != dimensionID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, dimensionID, new ModTeleporter(thePlayer.mcServer.worldServerForDimension(dimensionID), dimensionID, this, blockFrame));

			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new ModTeleporter(thePlayer.mcServer.worldServerForDimension(0), 0, this, blockFrame));
			}
		}
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(field_176550_a, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		for(int i = 0; i < 4; ++i) {
			double d0 = (double)((float)pos.getX() + rand.nextFloat());
			double d1 = (double)((float)pos.getY() + rand.nextFloat());
			double d2 = (double)((float)pos.getZ() + rand.nextFloat());
			double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if(worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}
			//EntityWastelandsPotalFX var20 = new EntityWastelandsPotalFX(worldIn, d0, d1, d2, d3, d4, d5);
			//FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	public boolean makePortal(World worldIn, BlockPos p_176548_2_) {
		BlockWastelandsPortal.Size size = new BlockWastelandsPortal.Size(worldIn, p_176548_2_, EnumFacing.Axis.X);
		if (size.func_150860_b() && size.field_150864_e == 0) {
			size.func_150859_c();
			return true;
		} else {
			BlockWastelandsPortal.Size size1 = new BlockWastelandsPortal.Size(worldIn, p_176548_2_, EnumFacing.Axis.Z);
			if (size1.func_150860_b() && size1.field_150864_e == 0) {
				size1.func_150859_c();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World w, BlockPos pos) {
		return Item.getItemFromBlock(this);
	}

	public int getMetaFromState(IBlockState state) {
		return func_176549_a((EnumFacing.Axis)state.getValue(field_176550_a));
	}

	public static int func_176549_a(EnumFacing.Axis p_176549_0_) {
		return p_176549_0_ == EnumFacing.Axis.X ? 1 : (p_176549_0_ == EnumFacing.Axis.Z ? 2 : 0);
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {field_176550_a});
	}

	public static class Size
	{
		private final World field_150867_a;
		private final EnumFacing.Axis field_150865_b;
		private final EnumFacing field_150866_c;
		private final EnumFacing field_150863_d;
		private int field_150864_e = 0;
		private BlockPos field_150861_f;
		private int field_150862_g;
		private int field_150868_h;
		private static final String __OBFID = "CL_00000285";

		public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
		{
			this.field_150867_a = worldIn;
			this.field_150865_b = p_i45694_3_;

			if (p_i45694_3_ == EnumFacing.Axis.X)
			{
				this.field_150863_d = EnumFacing.EAST;
				this.field_150866_c = EnumFacing.WEST;
			}
			else
			{
				this.field_150863_d = EnumFacing.NORTH;
				this.field_150866_c = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos1 = p_i45694_2_; p_i45694_2_.getY() > blockpos1.getY() - 21 && p_i45694_2_.getY() > 0 && this.func_150857_a(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
			{
				;
			}

			int i = this.func_180120_a(p_i45694_2_, this.field_150863_d) - 1;

			if (i >= 0)
			{
				this.field_150861_f = p_i45694_2_.offset(this.field_150863_d, i);
				this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);

				if (this.field_150868_h < 2 || this.field_150868_h > 21)
				{
					this.field_150861_f = null;
					this.field_150868_h = 0;
				}
			}

			if (this.field_150861_f != null)
			{
				this.field_150862_g = this.func_150858_a();
			}
		}

		protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_)
		{
			int i;

			for (i = 0; i < 22; ++i)
			{
				BlockPos blockpos1 = p_180120_1_.offset(p_180120_2_, i);

				if (!this.func_150857_a(this.field_150867_a.getBlockState(blockpos1).getBlock()) || this.field_150867_a.getBlockState(blockpos1.down()).getBlock() != EssenceBlocks.wastelandsPortalFrame)
				{
					break;
				}
			}

			Block block = this.field_150867_a.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == EssenceBlocks.wastelandsPortalFrame ? i : 0;
		}

		protected int func_150858_a()
		{
			int i;
			label56:

				for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
				{
					for (i = 0; i < this.field_150868_h; ++i)
					{
						BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i).up(this.field_150862_g);
						Block block = this.field_150867_a.getBlockState(blockpos).getBlock();

						if (!this.func_150857_a(block))
						{
							break label56;
						}

						if (block == EssenceBlocks.wastelandsPortal)
						{
							++this.field_150864_e;
						}

						if (i == 0)
						{
							block = this.field_150867_a.getBlockState(blockpos.offset(this.field_150863_d)).getBlock();

							if (block != EssenceBlocks.wastelandsPortalFrame)
							{
								break label56;
							}
						}
						else if (i == this.field_150868_h - 1)
						{
							block = this.field_150867_a.getBlockState(blockpos.offset(this.field_150866_c)).getBlock();

							if (block != EssenceBlocks.wastelandsPortalFrame)
							{
								break label56;
							}
						}
					}
				}

			for (i = 0; i < this.field_150868_h; ++i)
			{
				if (this.field_150867_a.getBlockState(this.field_150861_f.offset(this.field_150866_c, i).up(this.field_150862_g)).getBlock() != EssenceBlocks.wastelandsPortalFrame)
				{
					this.field_150862_g = 0;
					break;
				}
			}

			if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
			{
				return this.field_150862_g;
			}
			else
			{
				this.field_150861_f = null;
				this.field_150868_h = 0;
				this.field_150862_g = 0;
				return 0;
			}
		}

		protected boolean func_150857_a(Block p_150857_1_)
		{
			return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == EssenceBlocks.fire || p_150857_1_ == EssenceBlocks.wastelandsPortal;
		}

		public boolean func_150860_b()
		{
			return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
		}

		public void func_150859_c()
		{
			for (int i = 0; i < this.field_150868_h; ++i)
			{
				BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i);

				for (int j = 0; j < this.field_150862_g; ++j)
				{
					this.field_150867_a.setBlockState(blockpos.up(j), EssenceBlocks.wastelandsPortal.getDefaultState().withProperty(BlockWastelandsPortal.field_176550_a, this.field_150865_b), 2);
				}
			}
		}
	}
}*/