package net.jitl.common.item.gear;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public interface ILightGear {
    default double scaleWithDarkness(Entity entity, double original) {
        return original * (1 - entity.getBrightness());
    }
}
