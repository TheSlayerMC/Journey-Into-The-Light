package net.jitl.common.entity.frozen;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

public class ShattererEntity extends FlyingEntity implements IMob {

    public ShattererEntity(EntityType<? extends ShattererEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new ShattererEntity.MoveHelperController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new ShattererEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new ShattererEntity.LookAroundGoal(this));
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            Vector3d vector3d = this.shatterer.getDeltaMovement();
            this.shatterer.yRot = -((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI);
            this.shatterer.yBodyRot = this.shatterer.yRot;
        }
    }

    static class MoveHelperController extends MovementController {
        private final ShattererEntity shatterer;
        private int floatDuration;

        public MoveHelperController(ShattererEntity shatterer) {
            super(shatterer);
            this.shatterer = shatterer;
        }

        @Override
        public void tick() {
            if(this.operation == MovementController.Action.MOVE_TO) {
                if(this.floatDuration-- <= 0) {
                    this.floatDuration += this.shatterer.getRandom().nextInt(3) + 1;
                    Vector3d vector3d = new Vector3d(this.wantedX - this.shatterer.getX(), this.wantedY - this.shatterer.getY(), this.wantedZ - this.shatterer.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if(this.canReach(vector3d, MathHelper.ceil(d0))) {
                        this.shatterer.setDeltaMovement(this.shatterer.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = MovementController.Action.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vector3d vector3d_, int int_) {
            AxisAlignedBB axisalignedbb = this.shatterer.getBoundingBox();

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
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MovementController movementcontroller = this.shatterer.getMoveControl();
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
