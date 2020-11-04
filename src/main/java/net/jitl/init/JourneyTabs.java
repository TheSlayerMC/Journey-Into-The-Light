package net.jitl.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class JourneyTabs {

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