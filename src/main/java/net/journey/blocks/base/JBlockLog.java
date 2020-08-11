package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.CustomItemModelProvider;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for log blocks.
 * The item model for it should be placed to "models/item/block/log/" by default.
 */
public class JBlockLog extends BlockLog implements CustomItemModelProvider {

    public JBlockLog(String name, String enName) {
        super();
        setHardness(3.0F);
        StuffConstructor.regAndSetupBlock(this, name, enName, JourneyTabs.BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public boolean isWood(@NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSustainLeaves(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return true;
    }

    @Override
    public @NotNull BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState();

        EnumAxis axis;
        switch (meta) {
            case 1:
                axis = EnumAxis.X;
                break;
            case 2:
                axis = EnumAxis.Y;
                break;
            case 3:
                axis = EnumAxis.Z;
                break;
            default:
                axis = EnumAxis.NONE;
        }

        return state.withProperty(LOG_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(LOG_AXIS)) {
            case X:
                return 1;
            case Y:
                return 2;
            case Z:
                return 3;
            default:
                return 0;
        }
    }

    @NotNull
    @Override
    public ResourceLocation getItemModelResourceLocation() {
	    return new ResourceLocation(JITL.MOD_ID, "block/log/" + getRegistryName().getPath());
    }
}