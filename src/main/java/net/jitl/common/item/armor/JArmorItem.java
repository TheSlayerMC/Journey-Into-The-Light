package net.jitl.common.item.armor;

import net.jitl.JITL;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.init.JTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JArmorItem extends ArmorItem {

	public JArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
		super(materialIn, slotIn, new Item.Properties().tab(JTabs.ARMOR));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if (!worldIn.isClientSide) {
			itemstack.setDamageValue(getMaterial().getDurabilityForSlot(EquipmentSlotType.HEAD) / 2);
			System.out.println("World is server");
			System.out.println(getMaterial().getDurabilityForSlot(EquipmentSlotType.HEAD) / 2);
		}
		return ActionResult.pass(itemstack);
	}
}