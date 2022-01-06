package net.jitl.common.entity.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class IdleHealGoal extends Goal {
    private final Mob mob;
    private final float healAmount;

    public IdleHealGoal(Mob mob, int ticksToFull) {
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
