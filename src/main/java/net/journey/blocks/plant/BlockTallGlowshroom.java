package net.journey.blocks.plant;

import net.journey.blocks.base.JBlockDoublePlant;
import net.journey.init.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTallGlowshroom extends JBlockDoublePlant {
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
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 14500000;
    }
}