package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollAdmireItemTask<E extends FrozenTrollEntity> extends Task<E> {
    private final int admireDuration;

    public FrozenTrollAdmireItemTask(int int_) {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.ADMIRING_ITEM, MemoryModuleStatus.VALUE_ABSENT, MemoryModuleType.ADMIRING_DISABLED, MemoryModuleStatus.VALUE_ABSENT, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleStatus.VALUE_ABSENT));
        this.admireDuration = int_;
    }

    protected boolean checkExtraStartConditions(ServerWorld worldIn, E owner) {
        ItemEntity itementity = owner.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM).get();
        return FrozenTrollTasks.isLovedItem(itementity.getItem().getItem());
    }

    protected void start(@NotNull ServerWorld worldIn, E entityIn, long gameTimeIn) {
        entityIn.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, this.admireDuration);
    }
}
