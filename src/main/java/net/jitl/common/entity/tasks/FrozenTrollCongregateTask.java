package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableMap;
import net.jitl.core.init.JEntities;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;

import java.util.Optional;

public class FrozenTrollCongregateTask extends Behavior<LivingEntity> {
    public FrozenTrollCongregateTask() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel worldIn, LivingEntity owner) {
        Brain<?> brain = owner.getBrain();
        Optional<GlobalPos> optional = brain.getMemory(MemoryModuleType.MEETING_POINT);
        return worldIn.getRandom().nextInt(100) == 0 &&
                optional.isPresent() &&
                worldIn.dimension() == optional.get().dimension() &&
                optional.get().pos().closerThan(owner.position(), 4.0D) &&
                brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).get().contains((mob1) -> JEntities.FROZEN_TROLL_TYPE.equals(mob1.getType()));
    }

    @Override
    protected void start(ServerLevel worldIn, LivingEntity entityIn, long gameTimeIn) {
        Brain<?> brain = entityIn.getBrain();
        brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).flatMap((nearestVisibleLivingEntities) -> {
            return nearestVisibleLivingEntities.findClosest((livingEntity2) -> {
                return JEntities.FROZEN_TROLL_TYPE.equals(livingEntity2.getType()) && livingEntity2.distanceToSqr(entityIn) <= 32.0D;
            });
        }).ifPresent((livingEntity) -> {
            brain.setMemory(MemoryModuleType.INTERACTION_TARGET, livingEntity);
            brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityTracker(livingEntity, true));
            brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityTracker(livingEntity, false), 0.3F, 1));
        });
    }
}