package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollAdmireItemTask<E extends FrozenTrollEntity> extends Behavior<E> {
    private final int admireDuration;

    public FrozenTrollAdmireItemTask(int int_) {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_ABSENT, MemoryModuleType.ADMIRING_DISABLED, MemoryStatus.VALUE_ABSENT, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryStatus.VALUE_ABSENT));
        this.admireDuration = int_;
    }

    protected boolean checkExtraStartConditions(ServerLevel worldIn, E owner) {
        ItemEntity itementity = owner.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM).get();
        return FrozenTrollTasks.isLovedItem(itementity.getItem().getItem());
    }

    protected void start(@NotNull ServerLevel worldIn, E entityIn, long gameTimeIn) {
        entityIn.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, this.admireDuration);
    }
}
