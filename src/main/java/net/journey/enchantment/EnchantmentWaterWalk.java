package net.journey.enchantment;

import net.journey.init.JourneyEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class EnchantmentWaterWalk extends Enchantment {

    public EnchantmentWaterWalk() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setName("Water Walker");
        setRegistryName(new ResourceLocation(SlayerAPI.MOD_ID, "Water Walker"));
    }

    @Override
    public boolean canApply(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() instanceof ItemArmor;
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
        return super.canApplyTogether(e) && e != this || e != Enchantments.DEPTH_STRIDER;
    }
}