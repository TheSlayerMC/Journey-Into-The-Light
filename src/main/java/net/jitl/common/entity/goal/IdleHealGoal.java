package net.jitl.common.entity.goal;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class IdleHealGoal extends Goal {
    private final MobEntity mob;
    private final float healAmount;

    public IdleHealGoal(MobEntity mob, int ticksToFull) {
        this.mob = mob;
        healAmount = mob.getMaxHealth() / ticksToFull;
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() == null;
    }

    @Override
    public void tick() {
        mob.heal(healAmount);
    }
}
