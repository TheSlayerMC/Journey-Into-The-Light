package net.jitl.common.item.hoe;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LuniumHoeItem extends JHoeItem {
    public LuniumHoeItem(JToolTiers tier) {
        super(tier);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
        float value = tag.getFloat("cooldown");
        if (stack.getDamageValue() > 0 || value < 100) {
            if (value == 0) {
                tag.putFloat("cooldown", 100);
                stack.setDamageValue(stack.getDamageValue() - 1);
            } else {
                tag.putFloat("cooldown", Math.max(value - entityIn.getBrightness(), 0));
            }
            System.out.println(tag.getFloat("cooldown"));
            stack.setTag(tag);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack.equals(newStack)) return false;
        if (oldStack.sameItem(newStack)) {
            int durability = newStack.getDamageValue() - oldStack.getDamageValue();
            return durability != 0 && durability != 1; //for repair
        }
        return true;
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        if (oldStack.equals(newStack)) return false;
        if (oldStack.sameItem(newStack)) {
            int durability = newStack.getDamageValue() - oldStack.getDamageValue();
            return durability != 0 && durability != 1;
        }
        return true;
    }
}
