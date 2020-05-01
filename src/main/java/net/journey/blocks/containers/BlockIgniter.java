package net.journey.blocks.containers;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockIgniter extends BlockMod {

    public BlockIgniter(String name, String finalName) {
        super(name, finalName);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isFireSource(World w, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(JourneyBlocks.igniter);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos) && worldIn.isAirBlock(pos.up())) {
                worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
            }
            if (!worldIn.isBlockPowered(pos) && worldIn.getBlockState(pos.up()).getBlock() == Blocks.FIRE) {
                worldIn.setBlockToAir(pos.up());
                worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
            }
            if (worldIn.isBlockPowered(pos) && worldIn.getBlockState(pos.up()).getBlock() != Blocks.FIRE) {
                worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
            }
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos) && worldIn.isAirBlock(pos.up())) {
                worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
            }
            if (!worldIn.isBlockPowered(pos) && worldIn.getBlockState(pos.up()).getBlock() == Blocks.FIRE) {
                worldIn.setBlockToAir(pos.up());
                worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
            }
            if (worldIn.isBlockPowered(pos) && worldIn.getBlockState(pos.up()).getBlock() != Blocks.FIRE) {
                worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
            }
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (!worldIn.isBlockPowered(pos)) {
                worldIn.setBlockToAir(pos.up());
                worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
            }
        }
    }
}