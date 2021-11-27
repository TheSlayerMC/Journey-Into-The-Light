package net.jitl.common.entity.goal;

import java.util.EnumSet;

import net.jitl.common.entity.pet.MiniBoomEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.Goal.Flag;
import net.minecraft.entity.monster.CreeperEntity;

public class MiniBoomSwellGoal extends Goal {

    private final MiniBoomEntity boom;
    private LivingEntity target;

    public MiniBoomSwellGoal(MiniBoomEntity eboom) {
        this.boom = eboom;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity entity = this.boom.getTarget();
        return this.boom.getSwellDir() > 0 || entity != null && this.boom.distanceToSqr(entity) < 9.0D;
    }

    @Override
    public void start() {
        this.boom.getNavigation().stop();
        this.target = this.boom.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public void tick() {
        if (this.target == null) {
            this.boom.setSwellDir(-1);
        } else if (this.boom.distanceToSqr(this.target) > 49.0D) {
            this.boom.setSwellDir(-1);
        } else if (!this.boom.getSensing().canSee(this.target)) {
            this.boom.setSwellDir(-1);
        } else {
            this.boom.setSwellDir(1);
        }
    }
}
