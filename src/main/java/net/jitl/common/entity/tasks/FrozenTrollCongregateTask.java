package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.init.JEntities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Optional;

public class FrozenTrollCongregateTask extends Behavior<LivingEntity> {
    public FrozenTrollCongregateTask() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel worldIn, LivingEntity owner) {
        Brain<?> brain = owner.getBrain();
        Optional<GlobalPos> optional = brain.getMemory(MemoryModuleType.MEETING_POINT);
        return worldIn.getRandom().nextInt(100) == 0 &&
                optional.isPresent() &&
                worldIn.dimension() == optional.get().dimension() &&
                optional.get().pos().closerThan(owner.position(), 4.0D) &&
                brain.getMemory(MemoryModuleType.VISIBLE_LIVING_ENTITIES).get().stream().anyMatch((mob1) -> JEntities.FROZEN_TROLL_TYPE.equals(mob1.getType()));
    }

    protected void start(ServerLevel worldIn, LivingEntity entityIn, long gameTimeIn) {
        Brain<?> brain = entityIn.getBrain();
        brain.getMemory(MemoryModuleType.VISIBLE_LIVING_ENTITIES).flatMap(visibleMobs -> visibleMobs.stream().filter((mob) ->
                JEntities.FROZEN_TROLL_TYPE.equals(mob.getType())).filter((frozenTroll) ->
                frozenTroll.distanceToSqr(entityIn) <= 32.0D).findFirst()).ifPresent((entityInDistance) -> {
            brain.setMemory(MemoryModuleType.INTERACTION_TARGET, entityInDistance);
            brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityTracker(entityInDistance, true));
            brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityTracker(entityInDistance, false), 0.3F, 1));
        });
    }
}