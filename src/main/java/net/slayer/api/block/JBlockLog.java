package net.slayer.api.block;

import net.journey.api.block.IWithCustomItemPath;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.SlayerAPI;

public class JBlockLog extends BlockLog implements IWithCustomItemPath {

    public JBlockLog(String name, String enName) {
        super();
        StuffConstructor.regAndSetupBlock(this, name, enName, 3.0F, JourneyTabs.BLOCKS);
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
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

    @Override
    public ResourceLocation getItemModelResourceLocation() {
        return new ResourceLocation(SlayerAPI.MOD_ID, "block/log/" + getRegistryName().getPath());
    }
}