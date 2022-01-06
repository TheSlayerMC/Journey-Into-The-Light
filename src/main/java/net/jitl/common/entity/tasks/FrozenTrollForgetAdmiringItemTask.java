package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FrozenTrollForgetAdmiringItemTask<E extends FrozenTrollEntity> extends Behavior<E> {
    private final int maxDistanceToItem;

    public FrozenTrollForgetAdmiringItemTask(int int_) {
        super(ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.REGISTERED));
        this.maxDistanceToItem = int_;
    }

    protected boolean checkExtraStartConditions(ServerLevel worldIn, E owner) {
        if (!owner.getOffhandItem().isEmpty()) {
            return false;
        } else {
            Optional<ItemEntity> optional = owner.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
            return optional.map(itemEntity -> !itemEntity.closerThan(owner, this.maxDistanceToItem)).orElse(true);
        }
    }

    protected void start(@NotNull ServerLevel worldIn, E entityIn, long gameTimeIn) {
        entityIn.getBrain().eraseMemory(MemoryModuleType.ADMIRING_ITEM);
    }
}