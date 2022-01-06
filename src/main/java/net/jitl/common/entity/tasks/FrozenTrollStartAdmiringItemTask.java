package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.server.level.ServerLevel;

public class FrozenTrollStartAdmiringItemTask<E extends FrozenTrollEntity> extends Behavior<E> {
    public FrozenTrollStartAdmiringItemTask() {
        super(ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel worldIn, E owner) {
        return !owner.getOffhandItem().isEmpty() && !owner.getOffhandItem().isShield(owner);
    }

    protected void start(ServerLevel worldIn, E entityIn, long gameTimeIn) {
        FrozenTrollTasks.stopHoldingOffHandItem(entityIn, true);
    }
}
