package net.jitl.common.item;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.init.JTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class JArmorItem extends ArmorItem {

	public JArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
		super(materialIn, slotIn, new Item.Properties().tab(JTabs.ARMOR));
	}

	@Override
	public void inventoryTick(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isClientSide() && worldIn.getGameTime() % 120 == 0) {
			if (getMaterial() == JArmorMaterial.LUNIUM) {
				if (entityIn instanceof PlayerEntity) {
					if (isDayTime(worldIn) && worldIn.canSeeSky(entityIn.blockPosition())) {
						stack.hurt(-2, random, null);
					}
				}
			}
		}
	}

	public static boolean isDayTime(World world) {
		final long time = world.getDayTime() % 24000L;
		return time < 13000L || time > 23000L;
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