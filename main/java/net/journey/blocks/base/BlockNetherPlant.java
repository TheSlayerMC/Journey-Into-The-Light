package net.journey.blocks.base;

import java.util.Random;

import javax.annotation.Nullable;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockNetherPlant extends BlockMod implements IPlantable {

	private boolean damageWhenContact = false;
	private boolean isFrozenPlant = false;
    protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

	public BlockNetherPlant(String name, String finalName) {
		super(EnumMaterialTypes.PLANT, name, finalName, 0.0F);
		this.setLightLevel(1F);
		this.setTickRandomly(true);
		float f = 0.3F;
		this.setCreativeTab(JourneyTabs.decoration);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FLOWER_AABB;
    }

	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

	public BlockNetherPlant setContactDamage() {
		damageWhenContact = true;
		return this;
	}

	public BlockNetherPlant setFrozenPlant() {
		isFrozenPlant = true;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World w, BlockPos pos, Random random) {
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
		if(damageWhenContact) entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && 
				worldIn.getBlockState(pos.down()).getBlock() == Blocks.NETHERRACK
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSand
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.earthenNetherrack
				|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND 				
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.nethicGrass;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
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
			w.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, boolean b) {
		if (b)
			return worldIn.getBlockState(pos.down()).getBlock() == Blocks.NETHERRACK
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSand
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.earthenNetherrack
					|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND;
		else
			return worldIn.getBlockState(pos.down()).getBlock().getMaterial(getDefaultState()) == Material.GRASS;
	}

	@Override
	public BlockNetherPlant setLightLevel(float value) {
		this.lightValue = (int)(15.0F * value);
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
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

	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}
}