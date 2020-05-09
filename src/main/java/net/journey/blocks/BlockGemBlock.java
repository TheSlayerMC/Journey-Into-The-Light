package net.journey.blocks;

import net.journey.init.items.JourneyItems;
import net.journey.util.RandHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockGemBlock extends BlockMod {

    protected final boolean isRare;

    public BlockGemBlock(String name, String f, boolean isRare) {
        super(EnumMaterialTypes.GLASS, name, f, 0.4F);
        this.isRare = isRare;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Item gem = RandHelper.chooseEqual(RANDOM, JourneyItems.greenGem, JourneyItems.purpleGem, JourneyItems.blueGem, JourneyItems.yellowGem);

        int count = isRare ? RANDOM.nextInt(1) + 3 : RANDOM.nextInt(1) + 1;

        drops.add(new ItemStack(gem, count));
    }
}