package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FrozenTrollForgetAdmiringItemTask<E extends FrozenTrollEntity> extends Task<E> {
    private final int maxDistanceToItem;

    public FrozenTrollForgetAdmiringItemTask(int int_) {
        super(ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleStatus.REGISTERED));
        this.maxDistanceToItem = int_;
    }

    protected boolean checkExtraStartConditions(ServerWorld worldIn, E owner) {
        if (!owner.getOffhandItem().isEmpty()) {
            return false;
        } else {
            Optional<ItemEntity> optional = owner.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
            return optional.map(itemEntity -> !itemEntity.closerThan(owner, this.maxDistanceToItem)).orElse(true);
        }
    }

    protected void start(@NotNull ServerWorld worldIn, E entityIn, long gameTimeIn) {
        entityIn.getBrain().eraseMemory(MemoryModuleType.ADMIRING_ITEM);
    }
}