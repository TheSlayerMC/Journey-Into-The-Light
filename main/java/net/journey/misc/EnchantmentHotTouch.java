package net.journey.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class EnchantmentHotTouch extends Enchantment {

	public EnchantmentHotTouch(EntityEquipmentSlot ... slot) {
		super(Rarity.RARE, EnumEnchantmentType.DIGGER, slot);
		this.setName("Hot Touch");
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() instanceof ItemTool;
	}

    @Override
	public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public int getMinEnchantability(int par1) {
    	return 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        return super.canApplyTogether(e) && e.effectId != this.effectId && e.effectId != e.fortune.effectId && e.effectId != e.silkTouch.effectId;
    }
}