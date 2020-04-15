package net.journey.blocks.portal;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.client.render.particles.EntityBoilPotalFX;
import net.journey.dimension.ModTeleporter;
import net.journey.util.Config;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBoilPortal extends BlockModPortal {
	
	public BlockBoilPortal(String name) {
		super(name, "Boiling Portal");
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
			WorldServer worldserver = thePlayer.server.getWorld(thePlayer.dimension);
			//thePlayer.triggerAchievement(JourneyAchievements.achievementBoil);
			int dimensionID = Config.boil;
			Block blockFrame = JourneyBlocks.boilPortalFrame;
			if(thePlayer.timeUntilPortal > 0) 
				thePlayer.timeUntilPortal = 10;
			else if(thePlayer.dimension != dimensionID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.server.getPlayerList().transferPlayerToDimension(thePlayer, dimensionID, new ModTeleporter(thePlayer.server.getWorld(dimensionID), this, blockFrame.getDefaultState()));
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.server.getPlayerList().transferPlayerToDimension(thePlayer, 0, new ModTeleporter(thePlayer.server.getWorld(0), this, blockFrame.getDefaultState()));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if(rand.nextInt(100) == 0)
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);

		for(int i = 0; i < 4; ++i) {
			double d0 = (float)pos.getX() + rand.nextFloat();
			double d1 = (float)pos.getY() + rand.nextFloat();
			double d2 = (float)pos.getZ() + rand.nextFloat();
			double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if(worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * j;
				d3 = rand.nextFloat() * 2.0F * j;
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * j;
				d5 = rand.nextFloat() * 2.0F * j;
			}
			EntityBoilPotalFX var20 = new EntityBoilPotalFX(worldIn, d0, d1, d2, d3, d4, d5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	@Override
	public boolean makePortal(World worldIn, BlockPos p) {
		EntityLightningBolt bolt = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
		PortalSize size = new PortalSize(JourneyBlocks.boilPortalFrame, JourneyBlocks.boilPortal, worldIn, p, EnumFacing.Axis.X);
		if(size.isValid() && size.portalBlockCount == 0) {
			size.placePortalBlocks();
			worldIn.addWeatherEffect(bolt);
			worldIn.createExplosion(bolt, p.getX(), p.getY(), p.getZ(), 0.0F, true);
			return true;
		} else {
			EntityLightningBolt bolt1 = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
			size = size.spin(EnumFacing.Axis.Z);
			if(size.isValid() && size.portalBlockCount == 0) {
				size.placePortalBlocks();
				worldIn.addWeatherEffect(bolt1);
				worldIn.createExplosion(bolt1, p.getX(), p.getY(), p.getZ(), 0.0F, true);
				return true;
			} else {
				return false;
			}
		}
	}
}