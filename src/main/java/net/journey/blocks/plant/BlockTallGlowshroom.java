package net.journey.blocks.plant;

import net.journey.init.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.JDoubleBlockPlant;

import javax.annotation.Nullable;

public class BlockTallGlowshroom extends JDoubleBlockPlant {
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);

    public BlockTallGlowshroom(String name, String enName) {
        super(name, enName, JourneyTabs.DECORATION);
        setLightLevel(0.3F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BUSH_AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean hasAcceptableGround(World world, BlockPos pos) {
        return !world.isAirBlock(pos.down()) && super.hasAcceptableGround(world, pos);
    }

    @Override
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 220;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}