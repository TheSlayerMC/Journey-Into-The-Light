package net.jitl.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class LightweightEnchantment extends Enchantment {
    public LightweightEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slot) {
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
