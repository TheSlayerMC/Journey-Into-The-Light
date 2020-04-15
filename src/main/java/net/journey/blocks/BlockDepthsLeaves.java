package net.journey.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockDepthsLeaves extends BlockMod {

    public BlockDepthsLeaves(String name, String finalName, float hardness) {
        super(EnumMaterialTypes.LEAVES, name, finalName, hardness);
        this.setHardness(0.3F);
        isOpaque = false;
        isNormalCube = false;
        setLightOpacity(3);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}