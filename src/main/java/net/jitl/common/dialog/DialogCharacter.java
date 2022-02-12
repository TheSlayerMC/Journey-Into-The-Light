package net.jitl.common.dialog;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public record DialogCharacter(EntityType<? extends LivingEntity> entityType,
                              @Nullable String customName,
                              @Nullable CompoundTag data) {
    public DialogCharacter(EntityType<? extends LivingEntity> entityType) {
        this(entityType, null, null);
    }
}