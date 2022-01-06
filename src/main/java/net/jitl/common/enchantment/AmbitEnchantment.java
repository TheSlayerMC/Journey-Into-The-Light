package net.jitl.common.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public class AmbitEnchantment extends Enchantment {
    public AmbitEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slot) {
        super(rarityIn, typeIn, slot);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel) {
        return 12 + (enchantmentLevel - 1) * 20;
    }

    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 25;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }
}
