package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.List;

public class BloodcrustToolAbility implements IAbility {
    @Override
    public void breakBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
        CompoundNBT tag = stack.getTag();
        if (state.getBlock() instanceof AbstractFireBlock) {
            tag.putInt("Fire boost", 15);
            System.out.println("boost");
        } else {
            tag.putInt("Fire boost", Math.max(0, tag.getInt("Fire boost") - 1));
        }
        System.out.println(tag.getInt("Fire boost"));
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (!stack.hasTag()) stack.setTag(new CompoundNBT());
    }

    @Override
    public float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
        if (isCorrectTool(stack, state)) {
            original += (original * 2) * (((float) stack.getTag().getInt("Fire boost")) / 15);
        }
        return original;
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_tool");
        filler.addOverview();
        filler.addDrawback();
    }
}
