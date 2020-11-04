package net.jitl.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class JTabs {

    public static final ItemGroup ITEMS = new ItemGroup("journey.items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.GOLD_INGOT);
        }
    };
    public static final ItemGroup BLOCKS = new ItemGroup("journey.blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.PUMPKIN);
        }
    };
}