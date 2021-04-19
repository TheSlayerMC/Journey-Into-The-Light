package net.jitl.common.item.shovel;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class JShovelItem extends ShovelItem {

	public JShovelItem(JToolTiers tier) {
		super(tier, tier.getShovelDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if (!worldIn.isClientSide) {
			itemstack.setDamageValue(getTier().getUses() / 2);
		}
		return ActionResult.pass(itemstack);
	}
}