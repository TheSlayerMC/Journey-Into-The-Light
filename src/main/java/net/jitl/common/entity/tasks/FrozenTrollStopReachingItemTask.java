package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class FrozenTrollStopReachingItemTask<E extends FrozenTrollEntity> extends Task<E> {
    private final int maxTimeToReachItem;
    private final int disableTime;

    public FrozenTrollStopReachingItemTask(int int_, int int1_) {
        super(ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryModuleStatus.REGISTERED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleStatus.REGISTERED));
        this.maxTimeToReachItem = int_;
        this.disableTime = int1_;
    }

    protected boolean checkExtraStartConditions(ServerWorld worldIn, E owner) {
        return owner.getOffhandItem().isEmpty();
    }

    protected void start(ServerWorld worldIn, E entityIn, long gameTimeIn) {
        Brain<FrozenTrollEntity> brain = entityIn.getBrain();
        Optional<Integer> optional = brain.getMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
        if (!optional.isPresent()) {
            brain.setMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, 0);
        } else {
            int i = optional.get();
            if (i > this.maxTimeToReachItem) {
                brain.eraseMemory(MemoryModuleType.ADMIRING_ITEM);
                brain.eraseMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
                brain.setMemoryWithExpiry(MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, true, this.disableTime);
            } else {
                brain.setMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, i + 1);
            }
        }

    }
}