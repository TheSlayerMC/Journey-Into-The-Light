package net.journey.blocks;

import net.journey.client.render.particles.ParticleSwampFly;
import net.journey.init.JourneyTabs;
import net.journey.util.WorldUtils;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockSwampLamp extends BlockMod {

	protected String tex;

	public BlockSwampLamp(String name, String finalName, float hardness) {
		super(EnumMaterialTypes.GLASS, name, finalName, hardness);
		setCreativeTab(JourneyTabs.DECORATION);
		setTickRandomly(true);
		setLightLevel(0.33F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random rand) {
		float x = pos.getX() + .5F;
		float y = pos.getY() + .2F;
		float z = pos.getZ() + .5F;
		for (int l = 0; l < 6; ++l) {
			WorldUtils.spawnParticle(new ParticleSwampFly(w, x, y, z, 1.0F, 1.0F, 1.0F), w);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        float f = 0.0625F;
        return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
    }

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }
    
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}