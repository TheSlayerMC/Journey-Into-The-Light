package net.journey.blocks.plant;

import net.journey.api.block.base.JBlockPlant;
import net.journey.init.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class BlockGlowshroom extends JBlockPlant {

    public BlockGlowshroom(String name, String enName) {
        super(name, enName, JourneyTabs.DECORATION);
        setType(EnumPlantType.Cave);
        setLightLevel(0.3F);
    }

    @Override
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 14500000;
    }
}
