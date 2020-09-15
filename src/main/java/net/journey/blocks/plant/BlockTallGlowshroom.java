package net.journey.blocks.plant;

import net.journey.api.block.GroundPredicate;
import net.journey.blocks.base.JBlockDoublePlant;
import net.journey.init.JourneyTabs;
import net.journey.util.Config;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

public class BlockTallGlowshroom extends JBlockDoublePlant {
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);

    public BlockTallGlowshroom(String name, String enName) {
        super(name, enName, JourneyTabs.DECORATION);
        setGroundPredicate(GroundPredicate.SOLID_SIDE);
        setHardness(0);
        setLightLevel(0.3F);
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return BUSH_AABB;
    }

    @Override
    public int getPackedLightmapCoords(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return !Config.disableOverworldCaveBlockLuminance ? 14500000 : super.getPackedLightmapCoords(state, source, pos);
    }
}