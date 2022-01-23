package net.jitl.common.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public abstract class JFlyingEntity extends FlyingMob implements Enemy {

    public JFlyingEntity(EntityType<? extends JFlyingEntity> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new JFlyingEntity.MoveHelperController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new JFlyingEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new JFlyingEntity.LookAroundGoal(this));
        this.addGoals();
    }

    public abstract void addGoals();

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return despawnInPeaceful();
    }

    public abstract boolean despawnInPeaceful();

    static class LookAroundGoal extends Goal {
        private final JFlyingEntity entity;

        public LookAroundGoal(JFlyingEntity entity) {
            this.entity = entity;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            Vec3 vector3d = this.entity.getDeltaMovement();
            this.entity.setYRot(-((float) Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI));
            this.entity.yBodyRot = this.entity.getYRot();
        }
    }

    static class MoveHelperController extends MoveControl {
        private final JFlyingEntity entity;
        private int floatDuration;

        public MoveHelperController(JFlyingEntity entity) {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void tick() {
            if(this.operation == Operation.MOVE_TO) {
                if(this.floatDuration-- <= 0) {
                    this.floatDuration += this.entity.getRandom().nextInt(3) + 1;
                    Vec3 vector3d = new Vec3(this.wantedX - this.entity.getX(), this.wantedY - this.entity.getY(), this.wantedZ - this.entity.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if(this.canReach(vector3d, Mth.ceil(d0))) {
                        this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 vector3d_, int int_) {
            AABB axisalignedbb = this.entity.getBoundingBox();

            for(int i = 1; i < int_; ++i) {
                axisalignedbb = axisalignedbb.move(vector3d_);
                if (!this.entity.level.noCollision(this.entity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final JFlyingEntity entity;

        public RandomFlyGoal(JFlyingEntity entity) {
            this.entity = entity;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl movementcontroller = this.entity.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double d0 = movementcontroller.getWantedX() - this.entity.getX();
                double d1 = movementcontroller.getWantedY() - this.entity.getY();
                double d2 = movementcontroller.getWantedZ() - this.entity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            Random random = this.entity.getRandom();
            double d0 = this.entity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.entity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.entity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.entity.getMoveControl().setWantedPosition(d0, d1, d2, 0.3D);
        }
    }
}