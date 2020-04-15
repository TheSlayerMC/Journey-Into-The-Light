package net.journey.blocks;

import net.journey.init.items.JourneyItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.List;
import java.util.Random;

public class BlockGemBlock extends BlockMod {

    public boolean isRare;

    public BlockGemBlock(String name, String f, boolean rare) {
        super(EnumMaterialTypes.GLASS, name, f, 0.4F);
        isRare = rare;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = super.getDrops(world, pos, state, fortune);
        Item gem = JourneyItems.greenGem;
        switch (rand.nextInt(4)) {
            case 0:
                gem = JourneyItems.greenGem;
                break;
            case 1:
                gem = JourneyItems.purpleGem;
                break;
            case 2:
                gem = JourneyItems.blueGem;
                break;
            case 3:
                gem = JourneyItems.yellowGem;
                break;
        }
        if (isRare) drops.add(new ItemStack(gem, rand.nextInt(1) + 3));
        else drops.add(new ItemStack(gem, rand.nextInt(1) + 1));
        return drops;
    }
}