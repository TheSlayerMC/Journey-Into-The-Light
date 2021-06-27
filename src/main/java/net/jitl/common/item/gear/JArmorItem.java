package net.jitl.common.item.gear;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.init.JTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JArmorItem extends ArmorItem implements JGear {
	IAbility ability;

	public JArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn, IAbility armorAbility) {
		super(materialIn, slotIn, new Item.Properties().tab(JTabs.ARMOR));
		ability = armorAbility;
	}

	public IAbility getAbility() {
		return ability;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		ability.fillTooltips(stack, tooltip);
	}
}