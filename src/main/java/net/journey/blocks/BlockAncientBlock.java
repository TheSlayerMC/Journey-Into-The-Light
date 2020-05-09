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

public class BlockAncientBlock extends BlockMod {

    public BlockAncientBlock(String name, String f) {
        super(EnumMaterialTypes.STONE, name, f, 0.4F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Item dropItem = RandHelper.chooseEqual(RANDOM, JourneyItems.ancientPiece_1, JourneyItems.ancientPiece_2, JourneyItems.ancientPiece_3, JourneyItems.ancientPiece_4);
        int count = RANDOM.nextInt(1) + 1;

        if (RANDOM.nextInt(10) == 0) {
            dropItem = JourneyItems.untitled_disc;
            count = 1;
        }
        ItemStack drop = new ItemStack(dropItem, count);
        drops.add(drop);
    }
}