package net.jitl.common.item.gear;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface JTieredItemAbility {
    default void tick(LivingEntity entity, World world, ItemStack stack) {

    }
}
