package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

public class BlockModFlower extends BlockMod implements IPlantable {

	private boolean damageWhenContact = false;
	private boolean isFrozenPlant = false;

	public BlockModFlower(String name, String finalName) {
		super(EnumMaterialTypes.PLANT, name, finalName, 0.0F);
		this.setTickRandomly(true);
		float f = 0.3F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(JourneyTabs.decoration);
	}

	public BlockModFlower setContactDamage() {
		damageWhenContact = true;
		return this;
	}

	public BlockModFlower setFrozenPlant() {
		isFrozenPlant = true;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, BlockPos pos, IBlockState state, Random random) {
		if(isFrozenPlant) {
			if(random.nextInt(15) == 0) {
				for(int i = 0; i < 6; ++i) {
					double d0 = (double)pos.getX() + rand.nextDouble();
					double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.7D;
					double d2 = (double)pos.getZ() + rand.nextDouble();
					w.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(damageWhenContact) entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void updateTick(World w, BlockPos pos, IBlockState s, Random r) {
		this.checkAndDropBlock(w, pos, s);
	}

	protected void checkAndDropBlock(World w, BlockPos pos, IBlockState s) {
		if(!this.canBlockStay(w, pos, true)) {
			//if(this != EssenceBlocks.eucaTallGrass)
				this.dropBlockAsItem(w, pos, s, 0);
			w.setBlockState(pos, Blocks.air.getDefaultState(), 3);
		}
	}

	public boolean canBlockStay(World w, BlockPos pos, boolean b) {
		if(b) return w.getBlockState(pos.down()).getBlock().getMaterial() == Material.grass || w.getBlockState(pos.down()).getBlock().getMaterial() == Material.ground;
		else return w.getBlockState(pos.down()).getBlock().getMaterial() == Material.grass;
	}

	@Override
	public BlockModFlower setLightLevel(float value) {
		this.lightValue = (int)(15.0F * value);
		return this;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World w, BlockPos pos, IBlockState s) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		//if(this == EssenceBlocks.permaFlower || this == EssenceBlocks.shiverFlower || this == EssenceBlocks.iceBush)
		//	return EnumWorldBlockLayer.TRANSLUCENT;
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}

	@Override
	public boolean isReplaceable(World worldIn, BlockPos pos) {
		return true;
	}
}