package net.jitl.common.item.food;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.Item.Properties;

public class CrystalAppleItem extends Item {
    public CrystalAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
