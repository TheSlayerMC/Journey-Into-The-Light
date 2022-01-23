package net.jitl.common.entity.frozen;

import net.jitl.common.entity.base.JFlyingEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public class ShattererEntity extends JFlyingEntity implements Enemy {

    public ShattererEntity(EntityType<? extends ShattererEntity> type, Level worldIn) {
        super(type, worldIn);
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

    @Override
    public void addGoals() {

    }

    @Override
    public boolean despawnInPeaceful() {
        return true;
    }
}