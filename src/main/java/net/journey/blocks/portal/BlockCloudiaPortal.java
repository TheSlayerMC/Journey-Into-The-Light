package net.journey.blocks.portal;

import net.journey.blocks.base.JBlockPortal;
import net.journey.client.render.particles.EntityCloudiaPortalFX;
import net.journey.dimension.cloudia.TeleporterCloudia;
import net.journey.dimension.cloudia.TeleporterCloudiaToOverworld;
import net.journey.init.blocks.JourneyBlocks;
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
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCloudiaPortal extends JBlockPortal {

	public BlockCloudiaPortal(String name) {
		super(name, "Cloudia Portal", () -> JourneyBlocks.cloudiaPortalFrame);
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {

			EntityPlayerMP playerMP = (EntityPlayerMP) entity;
			Block blockFrame = JourneyBlocks.cloudiaPortalFrame;

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
			int dimensionID = Config.cloudia;
			int destination;
			if (entity.dimension == dimensionID) {
				destination = 0;
			} else {
				destination = dimensionID;
			}
			
			/*
			 * change player dimension to destination dimension based on current dim ID
			 */
			if (destination == 0) {
				entity.changeDimension(destination, new TeleporterCloudiaToOverworld(entity.getServer().getWorld(destination)));
			}
			
			if (destination == dimensionID) {
				entity.changeDimension(destination, new TeleporterCloudia(entity.getServer().getWorld(destination), playerMP));
				playerMP.setSpawnChunk(new BlockPos(playerMP), true, dimensionID);
			}
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0)
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);

        for (int i = 0; i < 4; ++i) {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (float) pos.getY() + rand.nextFloat();
            double d2 = (float) pos.getZ() + rand.nextFloat();
            double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
                d0 = (double) pos.getX() + 0.5D + 0.25D * j;
                d3 = rand.nextFloat() * 2.0F * j;
            } else {
                d2 = (double) pos.getZ() + 0.5D + 0.25D * j;
                d5 = rand.nextFloat() * 2.0F * j;
            }
            EntityCloudiaPortalFX var20 = new EntityCloudiaPortalFX(worldIn, d0, d1, d2, d3, d4, d5);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
        }
    }

    public boolean makePortal(World worldIn, BlockPos p) {
        EntityLightningBolt bolt = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
        PortalSize size = new PortalSize(JourneyBlocks.cloudiaPortalFrame, JourneyBlocks.cloudiaPortal, worldIn, p, EnumFacing.Axis.X);
	    if (size.isValid() && size.getPortalBlockCount() == 0) {
		    size.placePortalBlocks();
		    worldIn.addWeatherEffect(bolt);
		    worldIn.createExplosion(bolt, p.getX(), p.getY(), p.getZ(), 0.0F, true);
		    return true;
	    } else {
		    size = size.spin(EnumFacing.Axis.Z);
		    if (size.isValid() && size.getPortalBlockCount() == 0) {
			    size.placePortalBlocks();
			    worldIn.addWeatherEffect(bolt);
			    worldIn.createExplosion(bolt, p.getX(), p.getY(), p.getZ(), 0.0F, true);
			    return true;
		    } else {
			    return false;
		    }
        }
    }
}