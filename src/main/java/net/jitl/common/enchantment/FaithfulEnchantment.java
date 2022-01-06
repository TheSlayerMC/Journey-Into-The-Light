package net.jitl.common.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class FaithfulEnchantment extends Enchantment {
    public FaithfulEnchantment(Rarity rarityIn, EnchantmentCategory enchantmentType, EquipmentSlot... slots) {
        super(rarityIn, enchantmentType, slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel) {
        return 5 + enchantmentLevel * 7;
    }

    public int getMaxCost(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench);
    }
}