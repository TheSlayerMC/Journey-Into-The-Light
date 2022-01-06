package net.jitl.common.entity.frozen;

import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.phys.AABB;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;

public class ShattererEntity extends FlyingMob implements Enemy {

    public ShattererEntity(EntityType<? extends ShattererEntity> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new MoveHelperController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new LookAroundGoal(this));
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    static class LookAroundGoal extends Goal {
        private final ShattererEntity shatterer;

        public LookAroundGoal(ShattererEntity shatterer) {
            this.shatterer = shatterer;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            Vec3 vector3d = this.shatterer.getDeltaMovement();
            this.shatterer.yRot = -((float) Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI);
            this.shatterer.yBodyRot = this.shatterer.yRot;
        }
    }

    static class MoveHelperController extends MoveControl {
        private final ShattererEntity shatterer;
        private int floatDuration;

        public MoveHelperController(ShattererEntity shatterer) {
            super(shatterer);
            this.shatterer = shatterer;
        }

        @Override
        public void tick() {
            if(this.operation == Operation.MOVE_TO) {
                if(this.floatDuration-- <= 0) {
                    this.floatDuration += this.shatterer.getRandom().nextInt(3) + 1;
                    Vec3 vector3d = new Vec3(this.wantedX - this.shatterer.getX(), this.wantedY - this.shatterer.getY(), this.wantedZ - this.shatterer.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if(this.canReach(vector3d, Mth.ceil(d0))) {
                        this.shatterer.setDeltaMovement(this.shatterer.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 vector3d_, int int_) {
            AABB axisalignedbb = this.shatterer.getBoundingBox();

            for(int i = 1; i < int_; ++i) {
                axisalignedbb = axisalignedbb.move(vector3d_);
                if (!this.shatterer.level.noCollision(this.shatterer, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final ShattererEntity shatterer;

        public RandomFlyGoal(ShattererEntity shatterer) {
            this.shatterer = shatterer;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl movementcontroller = this.shatterer.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double d0 = movementcontroller.getWantedX() - this.shatterer.getX();
                double d1 = movementcontroller.getWantedY() - this.shatterer.getY();
                double d2 = movementcontroller.getWantedZ() - this.shatterer.getZ();
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
            Random random = this.shatterer.getRandom();
            double d0 = this.shatterer.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.shatterer.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.shatterer.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.shatterer.getMoveControl().setWantedPosition(d0, d1, d2, 0.3D);
        }
    }
}
