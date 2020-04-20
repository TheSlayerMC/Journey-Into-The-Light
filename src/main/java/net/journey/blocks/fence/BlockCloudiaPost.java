package net.journey.blocks.fence;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.block.BlockMod;

public class BlockCloudiaPost extends BlockMod {

    public BlockCloudiaPost(String name, String f) {
        super(name, f);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.3F, 0.0F, 0.3F, 0.6F, 1.0F, 0.6F);
    }
}