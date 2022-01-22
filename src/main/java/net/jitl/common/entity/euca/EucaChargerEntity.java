package net.jitl.common.entity.euca;

import net.jitl.core.JITL;
import net.jitl.core.init.world.JBiomeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

import java.util.Objects;
import java.util.Random;

public class EucaChargerEntity extends Monster {

    public EucaChargerEntity(EntityType<? extends EucaChargerEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canSpawn(EntityType<EucaChargerEntity> entity, ServerLevelAccessor s, MobSpawnType m, BlockPos p, Random r) {
        return !s.getBlockState(p).is(Blocks.WATER)
                && Objects.equals(s.getBiome(p), JBiomeRegistry.EUCA_PLAINS)
                || Objects.equals(s.getBiome(p), JBiomeRegistry.EUCA_GOLDITE_GRAINS);
    }
}
