package net.journey.blocks.plant;

import net.journey.api.block.base.JBlockPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGlowshroom extends JBlockPlant {

    public BlockGlowshroom(String name, String enName) {
        super(name, enName);
        setLightLevel(0.3F);
    }

    @Override
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 14500000;
    }
}
