package net.journey.dimension.corba.biomes;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class CorbaBiome extends JDimensionBiome {

    //super(new BiomePropertiesCorba("Corba Forest").setBaseHeight(0.2F).setHeightVariation(0.2F));

    public CorbaBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
        super(properties, topBlock, fillerBlock);
        this.topBlock = topBlock;
        this.fillerBlock = fillerBlock;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x5b592d;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x5b592d;
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

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random r, @NotNull ChunkPrimer c, int x, int z, double s) {
        IBlockState iblockstate = topBlock;
        IBlockState iblockstate1 = fillerBlock;
        int k = -1;
        int l = (int) (s / 3.0D + 3.0D + r.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;
        for (int k1 = 255; k1 >= 0; --k1) {
            if (k1 <= 1) {
                c.setBlockState(j1, k1, i1, Blocks.BEDROCK.getDefaultState());
            } else {
                IBlockState iblockstate2 = c.getBlockState(j1, k1, i1);

                if (iblockstate2.getMaterial() == Material.AIR) k = -1;
                else if (iblockstate2.getBlock() == JourneyBlocks.corbaStone) {
                    if (k == -1) {
                        if (l <= 0) {
                            iblockstate = null;
                            iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
                        } else if (k1 >= 7 && k1 <= 8) {
                            iblockstate = topBlock;
                            iblockstate1 = fillerBlock;
                        }

                        if (k1 < 8 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                            iblockstate = JourneyBlocks.corbaStone.getDefaultState();
                        k = l;
                        if (k1 >= 8) c.setBlockState(j1, k1, i1, iblockstate);
                        else if (k1 < 7 - l) {
                            iblockstate = null;
                            iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
                        } else c.setBlockState(j1, k1, i1, iblockstate1);
                    } else if (k > 0) {
                        --k;
                        c.setBlockState(j1, k1, i1, iblockstate1);
                    }
                }
            }
        }
    }
}