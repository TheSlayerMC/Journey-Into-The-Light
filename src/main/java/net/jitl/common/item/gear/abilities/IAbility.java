package net.jitl.common.item.gear.abilities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public interface IAbility {
    default void tick(LivingEntity entity, World world, ItemStack stack) {

    }

    default void attackTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {

    }

    default float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
        return original;
    }

    default void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {

    }

    default void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {

    }

    @OnlyIn(Dist.CLIENT)
    default void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {

    }

    default boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return original;
    }

    default FullArmorAbility getFullAbility(CompoundNBT nbt) {
        return null;
    }

    default boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
        return original;
    }

    interface INBTUpdateAbility extends IAbility {
        @Override
        default boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            if (oldStack.equals(newStack)) return false;
            if (oldStack.sameItem(newStack)) {
                int durability = oldStack.getDamageValue() - newStack.getDamageValue();
                return durability != 0 && durability != 1; //for repair
            }
            return true;
        }

        @Override
        default boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
            if (oldStack.equals(newStack)) return false;
            if (oldStack.sameItem(newStack)) {
                int durability = oldStack.getDamageValue() - newStack.getDamageValue();
                return durability != 0 && durability != 1; //for repair
            }
            return true;
        }

        @Override
        default void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (!stack.hasTag()) stack.setTag(new CompoundNBT());
        }
    }
}



