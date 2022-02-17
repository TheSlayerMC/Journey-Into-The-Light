package net.jitl.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HotTouchEnchantment extends Enchantment {

    public HotTouchEnchantment(Rarity rarity_, EnchantmentCategory category_, EquipmentSlot... applicableSlots_) {
        super(rarity_, category_, applicableSlots_);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 25;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return 50;
    }
}
