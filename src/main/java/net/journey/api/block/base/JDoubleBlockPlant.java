package net.journey.api.block.base;

import net.journey.api.block.IWithCustomItemPath;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;

/**
 * Base class for double plant blocks.
 * The item model for it should be placed to "models/item/block/" by default.
 */
public class JDoubleBlockPlant extends JDoubleBlock implements IWithCustomItemPath {
    private Block ground = null;

    public JDoubleBlockPlant(String name, String enName, CreativeTabs tab) {
        super(EnumMaterialTypes.PLANT, name, enName, 0, tab);
    }

    public JDoubleBlockPlant setAcceptableGround(Block ground) {
        this.ground = ground;

        return this;
    }

    /**
     * Checks if block at given position has acceptable ground to stay.
     *
     * @param world world where block is placed
     * @param pos   pos of  bottom plant block.
     *              You shouldn't get and check the blockstate of bottom plant block, because it may be not placed yet.
     */
    public boolean hasAcceptableGround(World world, BlockPos pos) {
        return (ground == null || world.getBlockState(pos.down()).getBlock() == ground) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    protected boolean canStay(World world, BlockPos pos, IBlockState state) {
        if (getHalf(state) == EnumHalf.BOTTOM && !hasAcceptableGround(world, pos)) {
            return false;
        }

        return super.canStay(world, pos, state);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return hasAcceptableGround(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public ResourceLocation getItemModelResourceLocation() {
        return new ResourceLocation(SlayerAPI.MOD_ID, "block/" + getRegistryName().getPath());
    }
}
