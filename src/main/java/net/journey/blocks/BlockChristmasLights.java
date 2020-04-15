package net.journey.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockChristmasLights extends BlockMod implements IShearable {

    public BlockChristmasLights(String name, String f) {
        super(EnumMaterialTypes.LEAVES, name, f, 0.2F);
        setLightLevel(0.35F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
        stack.add(new ItemStack(this));
        return stack;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }
}