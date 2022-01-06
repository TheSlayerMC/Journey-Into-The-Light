package net.jitl.common.item.gear;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.init.JTabs;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JSwordItem extends SwordItem implements JGear {
	IAbility ability;

	public JSwordItem(JToolTiers tier, IAbility swordAbility) {
		super(tier, (int) tier.getSwordDamage(), tier.getAttackSpeed(), new Properties().tab(JTabs.WEAPONS));
		ability = swordAbility;
	}

	@Override
	public IAbility getAbility() {
		return ability;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ability.rightClick(playerIn, handIn, worldIn);
		return super.use(worldIn, playerIn, handIn);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		ability.fillTooltips(stack, tooltip);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return ability.animate(super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged), oldStack, newStack, slotChanged);
	}

	@Override
	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
		return ability.resetBreak(super.shouldCauseBlockBreakReset(oldStack, newStack), oldStack, newStack);
	}
}