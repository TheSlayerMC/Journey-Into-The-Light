package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;

public class FrozenTrollStartAdmiringItemTask<E extends FrozenTrollEntity> extends Task<E> {
    public FrozenTrollStartAdmiringItemTask() {
        super(ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryModuleStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerWorld worldIn, E owner) {
        return !owner.getOffhandItem().isEmpty() && !owner.getOffhandItem().isShield(owner);
    }

    protected void start(ServerWorld worldIn, E entityIn, long gameTimeIn) {
        FrozenTrollTasks.stopHoldingOffHandItem(entityIn, true);
    }
}
