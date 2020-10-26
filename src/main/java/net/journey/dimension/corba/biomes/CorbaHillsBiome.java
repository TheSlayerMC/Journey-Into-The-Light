package net.journey.dimension.corba.biomes;

import net.journey.dimension.base.biome.EnumBiomeColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class CorbaHillsBiome extends CorbaBiome {

    public CorbaHillsBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
        super(properties, topBlock, fillerBlock);
        this.topBlock = topBlock;
        this.fillerBlock = fillerBlock;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
	    double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() * 0.0225D, (double) pos.getZ() * 0.0225D);
	    return this.getModdedBiomeGrassColor(d0 < -0.1D ? EnumBiomeColor.CORBA_FOREST.getInt() : EnumBiomeColor.CORBA_FOREST_2.getInt());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(0.455F, 0.216F, 5.0F).getRGB();
    }

    @Override
    public boolean canRain() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0xc6ff00;
    }
}