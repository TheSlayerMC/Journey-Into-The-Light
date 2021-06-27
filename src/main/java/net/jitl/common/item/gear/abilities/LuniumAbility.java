package net.jitl.common.item.gear.abilities;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.IAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public class LuniumAbility implements IAbility {
    @Override
    public void tick(LivingEntity entity, World world, ItemStack stack) {
        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
        float value = tag.getFloat("cooldown");
        if (stack.getDamageValue() > 0 || value < 100) {
            if (value == 0) {
                tag.putFloat("cooldown", 100);
                stack.setDamageValue(stack.getDamageValue() - 1);
                System.out.println(stack.getDamageValue());
            } else {
                tag.putFloat("cooldown", Math.max(value - entity.getBrightness(), 0)); //TODO: optimize counter
            }
            System.out.println(tag.getFloat("cooldown"));
            stack.setTag(tag); //TODO: it may be possible to remove this statement
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
    }

    @Override
    public boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack.equals(newStack)) return false;
        if (oldStack.sameItem(newStack)) {
            int durability = oldStack.getDamageValue() - newStack.getDamageValue();
            return durability != 0 && durability != 1; //for repair
        }
        return true;
    }

    @Override
    public boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
        if (oldStack.equals(newStack)) return false;
        if (oldStack.sameItem(newStack)) {
            int durability = oldStack.getDamageValue() - newStack.getDamageValue();
            return durability != 0 && durability != 1; //for repair
        }
        return true;
    }
}
