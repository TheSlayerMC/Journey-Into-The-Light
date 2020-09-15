package net.journey.blocks.plant;

import net.journey.api.block.GroundPredicate;
import net.journey.blocks.base.JBlockPlant;
import net.journey.util.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGlowshroom extends JBlockPlant {

    public BlockGlowshroom(String name, String enName) {
	    super(name, enName);
	    setGroundPredicate(GroundPredicate.SOLID_SIDE);
	    setHardness(0);
	    setLightLevel(0.3F);
    }

    @Override
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return !Config.disableOverworldCaveBlockLuminance ? 14500000 : super.getPackedLightmapCoords(state, source, pos);
    }
}
