package net.journey.blocks.portal;

import net.journey.JITL;
import net.journey.dimension.depths.TeleporterDepths;
import net.journey.enums.EnumParticlesClasses;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.List;
import java.util.Random;

public class BlockDepthsPortal extends BlockMod {

    public BlockDepthsPortal(String name, String f) {
        super(EnumMaterialTypes.PORTAL, name, f, 1.0F);
        this.setTickRandomly(true);
	    setHardness(-1F);
	    setCreativeTab(JourneyTabs.PORTAL_BLOCKS);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5345F, 1.0F);
    }

    @Override
    public boolean isPassable(IBlockAccess access, BlockPos pos) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if (this.getMaterial(getDefaultState()) == Material.PORTAL) {
            if (blockState != iblockstate) {
                return true;
            }
            if (block == this) {
                return false;
            }
        }
        return block != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {
			
			EntityPlayerMP playerMP = (EntityPlayerMP) entity;
			Block blockFrame = JourneyBlocks.depthsPortalFrame;

			/*
			 * sets timer for dimension travel
			 */
			if (entity.timeUntilPortal > 0) {
				return;
			}
			entity.timeUntilPortal = 75;

			/*
			 * sets destination
			 * 
			 * if player is in 'dimensionID' dimension, send player to overworld
			 * otherwise, send player to 'dimensionID' dimension
			 */
			int dimensionID = Config.depths;
			int destination;
			if (entity.dimension == dimensionID) {
				destination = 0;
			} else {
				destination = dimensionID;
			}
			
			/*
			 * change player dimension to destination dimension based on current dim ID
			 */
			entity.changeDimension(destination, new TeleporterDepths(entity.getServer().getWorld(destination)));
			
			if (destination == dimensionID) {
				playerMP.setSpawnChunk(new BlockPos(playerMP), true, dimensionID);
			}
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        double d0 = (float) pos.getX() + rand.nextFloat();
        double d1 = (float) pos.getY() + 0.8F;
        double d2 = (float) pos.getZ() + rand.nextFloat();
        double d3 = 0.0D;
        double d4 = 0.0D;
        double d5 = 0.0D;
        JITL.proxy.spawnParticle(EnumParticlesClasses.DEPTHS, worldIn, d0, d1, d2, d3, d4, d5);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, d3, d4, d5);
    }
}