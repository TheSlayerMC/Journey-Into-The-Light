package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.util.List;

public class BloodcrustToolAbility implements IAbility {
    @Override
    public void breakBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        CompoundTag tag = stack.getTag();
        if (state.getBlock() instanceof BaseFireBlock) {
            tag.putInt("Fire boost", 16);
            System.out.println("boost");
        } else {
            tag.putInt("Fire boost", Math.max(0, tag.getInt("Fire boost") - 1));
        }
        System.out.println(tag.getInt("Fire boost"));
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (!stack.hasTag()) stack.setTag(new CompoundTag());
    }

    @Override
    public float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
        if (isCorrectTool(stack, state)) {
            original += (original * 2) * (((float) stack.getTag().getInt("Fire boost")) / 16);
        }
        return original;
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_tool");
        filler.addOverview();
        filler.addDrawback();
        filler.addBreak();
        filler.addValue((200 * (float) stack.getTag().getInt("Fire boost") / 16));
    }
}
