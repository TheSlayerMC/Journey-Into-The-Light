package net.jitl.common.item.gear.abilities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public interface IAbility {
    default void tick(LivingEntity entity, World world, ItemStack stack) {

    }

    default void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {

    }

    default void damageTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {

    }

    default void breakBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {

    }

    default float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
        return original;
    }

    default boolean isCorrectTool(ItemStack stack, BlockState state) {
        Item item = stack.getItem();
        if (((TieredItem) item).getTier().getLevel() >= state.getHarvestLevel()) {
            for (ToolType type : stack.getToolTypes()) {
                if (state.isToolEffective(type)) return true;
            }
        }
        return false;
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



