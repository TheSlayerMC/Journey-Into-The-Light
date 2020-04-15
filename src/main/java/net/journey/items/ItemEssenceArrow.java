package net.journey.items;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.init.JourneyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemEssenceArrow extends ItemMod {

    public ItemEssenceArrow(String name, String finalname) {
        super(name, finalname);
        this.setCreativeTab(JourneyTabs.WEAPONS);
    }

    public EntityEssenceArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        EntityEssenceArrow e = new EntityEssenceArrow(worldIn);
        return null;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == ItemEssenceArrow.class;
    }
}