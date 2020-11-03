package net.jitl.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class JourneyTabs extends ItemGroup {

    public static JourneyTabs items;
    public static JourneyTabs blocks;

    private Item icon;

    public JourneyTabs(String label, Item icon) {
        super(label);
        this.icon = icon;

    }

    public static void init() {
        items = new JourneyTabs("journey.items", Items.GOLD_INGOT);
        blocks = new JourneyTabs("journey.blocks", Item.getItemFromBlock(Blocks.PUMPKIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack createIcon() {
        return new ItemStack(this.icon);
    }
}