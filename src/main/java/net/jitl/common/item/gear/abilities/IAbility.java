package net.jitl.common.item.gear.abilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public interface IAbility {
    /*
    TODO:Detailed testing and code review of all abilities
     -Diamond (but if someone else sees this close to release remind me)
     */

    //TODO: hasTag() might not be needed. Test

    //TODO: check if value rounding is ideal
    default void tick(LivingEntity entity, Level world, ItemStack stack) {

    }

    default void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {

    }

    default void damageTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {

    }

    default void rightClick(Player player, InteractionHand hand, Level world) {

    }

    default void breakBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {

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

    default void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {

    }

    default void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {

    }

    @OnlyIn(Dist.CLIENT)
    default void fillTooltips(ItemStack stack, List<Component> tooltip) {

    }

    default boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return original;
    }

    default FullArmorAbility getFullAbility(CompoundTag nbt) {
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
        default void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
            if (!stack.hasTag()) stack.setTag(new CompoundTag());
        }
    }
}



