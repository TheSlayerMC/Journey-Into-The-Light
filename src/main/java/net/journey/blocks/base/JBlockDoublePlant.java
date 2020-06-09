package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.IHasCustomItemPath;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

/**
 * Base class for double plant blocks.
 * The item model for it should be placed to "models/item/block/" by default.
 */
public class JBlockDoublePlant extends JDoubleBlock implements IHasCustomItemPath {
    private Predicate<IBlockState> groundPredicate = null;

    public JBlockDoublePlant(String name, String enName, @Nullable CreativeTabs tab) {
        super(EnumMaterialTypes.PLANT, name, enName, 0, tab);
    }

    public JBlockDoublePlant setAcceptableGround(Predicate<IBlockState> groundPredicate) {
        this.groundPredicate = groundPredicate;
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
        return (groundPredicate == null || groundPredicate.test(world.getBlockState(pos.down()))) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP);
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
    
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
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

    @NotNull
    @Override
    public ResourceLocation getItemModelResourceLocation() {
        return new ResourceLocation(JITL.MOD_ID, "block/" + getRegistryName().getPath());
    }

    @Override
    @javax.annotation.Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
}
