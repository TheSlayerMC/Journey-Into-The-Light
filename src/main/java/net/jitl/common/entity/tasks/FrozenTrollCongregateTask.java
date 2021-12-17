package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.init.JEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.memory.WalkTarget;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.util.math.EntityPosWrapper;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class FrozenTrollCongregateTask extends Task<LivingEntity> {
    public FrozenTrollCongregateTask() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryModuleStatus.REGISTERED, MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_LIVING_ENTITIES, MemoryModuleStatus.VALUE_PRESENT, MemoryModuleType.INTERACTION_TARGET, MemoryModuleStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerWorld worldIn, LivingEntity owner) {
        Brain<?> brain = owner.getBrain();
        Optional<GlobalPos> optional = brain.getMemory(MemoryModuleType.MEETING_POINT);
        return worldIn.getRandom().nextInt(100) == 0 &&
                optional.isPresent() &&
                worldIn.dimension() == optional.get().dimension() &&
                optional.get().pos().closerThan(owner.position(), 4.0D) &&
                brain.getMemory(MemoryModuleType.VISIBLE_LIVING_ENTITIES).get().stream().anyMatch((mob1) -> JEntities.FROZEN_TROLL_TYPE.equals(mob1.getType()));
    }

    protected void start(ServerWorld worldIn, LivingEntity entityIn, long gameTimeIn) {
        Brain<?> brain = entityIn.getBrain();
        brain.getMemory(MemoryModuleType.VISIBLE_LIVING_ENTITIES).flatMap(visibleMobs -> visibleMobs.stream().filter((mob) ->
                JEntities.FROZEN_TROLL_TYPE.equals(mob.getType())).filter((frozenTroll) ->
                frozenTroll.distanceToSqr(entityIn) <= 32.0D).findFirst()).ifPresent((entityInDistance) -> {
            brain.setMemory(MemoryModuleType.INTERACTION_TARGET, entityInDistance);
            brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityPosWrapper(entityInDistance, true));
            brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityPosWrapper(entityInDistance, false), 0.3F, 1));
        });
    }
}