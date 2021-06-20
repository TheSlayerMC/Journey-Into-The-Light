package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.init.JTabs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JArmorItem extends ArmorItem {

	public JArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
		super(materialIn, slotIn, new Item.Properties().tab(JTabs.ARMOR));
	}

	public void armorTickAbility(LivingEntity entity, World world, ItemStack stack) {}
}