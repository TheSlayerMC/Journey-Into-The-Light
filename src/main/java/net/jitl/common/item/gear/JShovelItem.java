package net.jitl.common.item.gear;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.init.JTabs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JShovelItem extends ShovelItem implements JGear {
	IAbility ability;

	public JShovelItem(JToolTiers tier, IAbility shovelAbility) {
		super(tier, tier.getShovelDam(), tier.getAttackSpeed(), new Properties().tab(JTabs.TOOLS));
		ability = shovelAbility;
	}

	@Override
	public IAbility getAbility() {
		return ability;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		ability.fillTooltips(stack, tooltip);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return ability.blockBreakSpeed(stack, state, super.getDestroySpeed(stack, state));
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return ability.animate(super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged), oldStack, newStack, slotChanged);
	}

	@Override
	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
		return ability.resetBreak(super.shouldCauseBlockBreakReset(oldStack, newStack), oldStack, newStack);
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		super.mineBlock(stack, worldIn, state, pos, entityLiving);
		ability.breakBlock(stack, worldIn, state, pos, entityLiving);
		return true;
	}
}