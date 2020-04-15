package net.journey.blocks.portal;

import net.journey.JourneyBlocks;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTerraniaPortal extends BlockModPortal {


    public BlockTerraniaPortal(String name) {
        super(name, "Terrania Portal");
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if ((entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {
            EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
            WorldServer worldserver = thePlayer.server.getWorld(thePlayer.dimension);
            //thePlayer.triggerAchievement(JourneyAchievements.achievementTerrania);
            int dimensionID = Config.terrania;
            Block blockFrame = JourneyBlocks.terraniaPortalFrame;
            if (thePlayer.timeUntilPortal > 0)
                thePlayer.timeUntilPortal = 10;
            else if (thePlayer.dimension != dimensionID) {
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
            //EntityTerraniaPotalFX var20 = new EntityTerraniaPotalFX(worldIn, d0, d1, d2, d3, d4, d5);
            //FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        pos = pos.offset(side);
        EnumFacing.Axis enumfacing$axis = null;

        if (blockState.getBlock() == this) {
            enumfacing$axis = blockState.getValue(AXIS);
            if (enumfacing$axis == null)
                return false;
            if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
                return false;
            if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
                return false;
        }

        boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
        boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;

        if (flag4 && side == EnumFacing.WEST) return true;
        else if (flag4 && side == EnumFacing.EAST) return true;
        else if (flag5 && side == EnumFacing.NORTH) return true;
        else return flag5 && side == EnumFacing.SOUTH;
    }

    @Override
    public boolean makePortal(World worldIn, BlockPos p) {
        EntityLightningBolt bolt = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
        PortalSize size = new PortalSize(JourneyBlocks.terraniaPortalFrame, JourneyBlocks.terraniaPortal, worldIn, p, EnumFacing.Axis.X);
        if (size.isValid() && size.portalBlockCount == 0) {
            size.placePortalBlocks();
            worldIn.addWeatherEffect(bolt);
            worldIn.createExplosion(bolt, p.getX(), p.getY(), p.getZ(), 0.0F, true);
            return true;
        } else {
            size = size.spin(EnumFacing.Axis.Z);
            if (size.isValid() && size.portalBlockCount == 0) {
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