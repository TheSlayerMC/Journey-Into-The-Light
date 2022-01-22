package net.jitl.common.entity.euca;

import net.jitl.core.init.world.JBiomeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Random;

public class ShimmererEntity extends FlyingMob implements Enemy {

    public ShimmererEntity(EntityType<? extends ShimmererEntity> type, Level worldIn) {
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

    public static boolean canSpawn(EntityType<ShimmererEntity> entity, ServerLevelAccessor s, MobSpawnType m, BlockPos p, Random r) {
        return !s.getBlockState(p).is(Blocks.WATER)
                && Objects.equals(s.getBiome(p), JBiomeRegistry.EUCA_PLAINS)
                || Objects.equals(s.getBiome(p), JBiomeRegistry.EUCA_GOLDITE_GRAINS);
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
        private final ShimmererEntity shimmerer;

        public LookAroundGoal(ShimmererEntity shimmerer) {
            this.shimmerer = shimmerer;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            Vec3 vector3d = this.shimmerer.getDeltaMovement();
            this.shimmerer.setYRot(-((float) Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI));
            this.shimmerer.yBodyRot = this.shimmerer.getYRot();
        }
    }

    static class MoveHelperController extends MoveControl {
        private final ShimmererEntity shimmerer;
        private int floatDuration;

        public MoveHelperController(ShimmererEntity shimmerer) {
            super(shimmerer);
            this.shimmerer = shimmerer;
        }

        @Override
        public void tick() {
            if(this.operation == Operation.MOVE_TO) {
                if(this.floatDuration-- <= 0) {
                    this.floatDuration += this.shimmerer.getRandom().nextInt(3) + 1;
                    Vec3 vector3d = new Vec3(this.wantedX - this.shimmerer.getX(), this.wantedY - this.shimmerer.getY(), this.wantedZ - this.shimmerer.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if(this.canReach(vector3d, Mth.ceil(d0))) {
                        this.shimmerer.setDeltaMovement(this.shimmerer.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 vector3d_, int int_) {
            AABB axisalignedbb = this.shimmerer.getBoundingBox();

            for(int i = 1; i < int_; ++i) {
                axisalignedbb = axisalignedbb.move(vector3d_);
                if (!this.shimmerer.level.noCollision(this.shimmerer, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final ShimmererEntity shimmerer;

        public RandomFlyGoal(ShimmererEntity shimmerer) {
            this.shimmerer = shimmerer;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl movementcontroller = this.shimmerer.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double d0 = movementcontroller.getWantedX() - this.shimmerer.getX();
                double d1 = movementcontroller.getWantedY() - this.shimmerer.getY();
                double d2 = movementcontroller.getWantedZ() - this.shimmerer.getZ();
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
            Random random = this.shimmerer.getRandom();
            double d0 = this.shimmerer.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.shimmerer.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.shimmerer.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.shimmerer.getMoveControl().setWantedPosition(d0, d1, d2, 0.3D);
        }
    }
}
