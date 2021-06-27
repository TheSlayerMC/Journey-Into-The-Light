package net.jitl.common.item.gear;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public interface IAbility {

    default void tick(LivingEntity entity, World world, ItemStack stack) {

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

    default boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
        return original;
    }
}
