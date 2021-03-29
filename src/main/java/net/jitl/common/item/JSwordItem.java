package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gearabilities.BaseArmorAbilities;
import net.jitl.common.item.gearabilities.BaseToolAbilities;
import net.jitl.init.JTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JSwordItem extends SwordItem {

	public JSwordItem(JToolTiers tier) {
		super(tier, (int) tier.getSwordDamage(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.WEAPONS));
	}

	@Override
	public void inventoryTick(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isClientSide()) {
			((JToolTiers) this.getTier()).getAbilities().swordTick(stack, worldIn, entityIn, itemSlot, isSelected);
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
			itemstack.setDamageValue(getTier().getUses() / 2);
		}
		return ActionResult.pass(itemstack);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> text, ITooltipFlag flag) {
		BaseToolAbilities ability = ((JToolTiers) this.getTier()).getAbilities();
		if (ability != null) {
			ability.fillSwordTooltip(new TooltipFiller(text, this.getRegistryName().getPath()));
		}
	}
}