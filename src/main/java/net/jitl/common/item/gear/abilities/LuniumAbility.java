package net.jitl.common.item.gear.abilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public class LuniumAbility implements IAbility.INBTUpdateAbility {
    @Override
    public void tick(LivingEntity entity, World world, ItemStack stack) {
        if (!world.isClientSide()) {
            CompoundNBT tag = stack.getTag();
            float value = tag.getFloat("cooldown");
            if (stack.getDamageValue() > 0 || value < 100) {
                value -= entity.getBrightness();
                if (value <= 0) {
                    value = 100;
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
                tag.putFloat("cooldown", value);
                System.out.println(tag.getFloat("cooldown"));
            }
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
        if (stack.getDamageValue() > 0) {
            filler.addBreak();
            filler.addValue((int) stack.getTag().getFloat("cooldown") / 20); //TODO: Not counting seconds!
        }
    }
}
