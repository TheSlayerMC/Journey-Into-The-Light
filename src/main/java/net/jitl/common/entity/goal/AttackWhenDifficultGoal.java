package net.jitl.common.entity.goal;

import net.jitl.common.entity.IDontAttackWhenPeaceful;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class AttackWhenDifficultGoal extends TargetGoal {
    
    private final IDontAttackWhenPeaceful mob;
    private final Mob entity;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public AttackWhenDifficultGoal(Mob entity, IDontAttackWhenPeaceful mob) {
        super(entity, false);
        this.entity = entity;
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        this.ownerLastHurtBy = entity.getLastHurtByMob();
        int i = entity.getLastHurtByMobTimestamp();
        return i != this.timestamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT) && this.mob.wantsToAttack(this.ownerLastHurtBy, entity);
    }

    @Override
    public void start() {
        this.entity.setTarget(this.ownerLastHurtBy);
        this.timestamp = entity.getLastHurtByMobTimestamp();
        super.start();
    }
}