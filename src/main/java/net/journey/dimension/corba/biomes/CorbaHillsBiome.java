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
	    return EnumBiomeColor.CORBA_FOREST.getInt();
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