package net.slayer.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;

public class BlockModLog extends BlockMod {

    public BlockModLog(String name, String finalName) {
        super(EnumMaterialTypes.WOOD, name, finalName, 3.0F);
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
        return BlockRenderLayer.TRANSLUCENT;
    }
}