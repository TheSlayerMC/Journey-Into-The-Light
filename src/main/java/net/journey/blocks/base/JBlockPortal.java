package net.journey.blocks.base;

import net.journey.blocks.portal.PortalSize;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.function.Supplier;

import static net.minecraft.util.EnumFacing.*;

public abstract class JBlockPortal extends BlockPortal {
    private final Supplier<Block> frameSupplier;

    public JBlockPortal(String name, String enName, Supplier<Block> frame) {
        this.frameSupplier = frame;
        setHardness(-1.0F);
        setSoundType(SoundType.GLASS);
        setLightLevel(0.75F);
        setCreativeTab(JourneyTabs.PORTAL_BLOCKS);
        StuffConstructor.regAndSetupBlock(this, name, enName, JourneyTabs.PORTAL_BLOCKS);
    }

    public abstract boolean makePortal(World worldIn, BlockPos p);

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        pos = pos.offset(side);
        Axis enumfacing$axis = null;

        if (blockState.getBlock() == this) {
            enumfacing$axis = blockState.getValue(AXIS);
            if (enumfacing$axis == null)
                return false;
            if (enumfacing$axis == Axis.Z && side != EAST && side != WEST)
                return false;
            if (enumfacing$axis == Axis.X && side != SOUTH && side != NORTH)
                return false;
        }

        boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || enumfacing$axis == Axis.X;
        boolean flag5 = flag2 || flag3 || enumfacing$axis == Axis.Z;

        if (flag4 && side == WEST) return true;
        else if (flag4 && side == EAST) return true;
        else if (flag5 && side == NORTH) return true;
        else return flag5 && side == SOUTH;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        Axis axis = state.getValue(AXIS);

        if (axis == Axis.X || axis == Axis.Z) {
            PortalSize portalSize = new PortalSize(getFrameBlock(), this, worldIn, pos, axis);

            if (!portalSize.isValid() || portalSize.getPortalBlockCount() < portalSize.getWidth() * portalSize.getHeight()) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

    }

    public Block getFrameBlock() {
        return frameSupplier.get();
    }
}