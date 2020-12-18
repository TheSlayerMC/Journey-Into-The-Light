package net.jitl.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SelfRepairingItem extends Item {
	public SelfRepairingItem(Properties properties) {
		super(properties);
	}

	//THIS IS SO FUCKING STUPID. HOLY SHIT.
	@Override
	public void inventoryTick(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn.tickCount % 20 == 0) {
			System.out.println("Tick Count");
			if (entityIn instanceof PlayerEntity) {
				System.out.println("Entity Instance");
				if (worldIn.isDay() && worldIn.getBrightness(LightType.SKY, entityIn.blockPosition()) > 15 /*THIS DOESN'T EVEN WORK??*/) {
					System.out.println("Sky Light");
					stack.hurt(-2, random, null);
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		//immediately resets to full value. what the fuck.
		itemstack.hurt(40, random, null);
		return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
	}
}