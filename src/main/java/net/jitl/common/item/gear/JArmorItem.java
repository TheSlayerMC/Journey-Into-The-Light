package net.jitl.common.item.gear;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.init.JTabs;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JArmorItem extends ArmorItem implements JGear {
	IAbility ability;

	public JArmorItem(JArmorMaterial materialIn, EquipmentSlot slotIn, IAbility armorAbility) {
		super(materialIn, slotIn, new Properties().tab(JTabs.ARMOR));
		ability = armorAbility;
	}

	public IAbility getAbility() {
		return ability;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		ability.fillTooltips(stack, tooltip);
	}
}