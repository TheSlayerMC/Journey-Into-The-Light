package net.jitl.common.entity.euca;

import net.jitl.common.entity.base.JFlyingEntity;
import net.jitl.core.JITL;
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

public class ShimmererEntity extends JFlyingEntity implements Enemy {

    public ShimmererEntity(EntityType<? extends ShimmererEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void addGoals() {

    }

    @Override
    public boolean despawnInPeaceful() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D);
    }

    public static boolean canSpawn(EntityType<ShimmererEntity> entity, ServerLevelAccessor s, MobSpawnType m, BlockPos p, Random r) {
        return !s.getBlockState(p).is(Blocks.WATER) ||
                Objects.equals(s.getBiome(p).getRegistryName(), JITL.rl("euca_plains")) ||
                Objects.equals(s.getBiome(p).getRegistryName(), JITL.rl("euca_goldite_grains"));
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
    }
}