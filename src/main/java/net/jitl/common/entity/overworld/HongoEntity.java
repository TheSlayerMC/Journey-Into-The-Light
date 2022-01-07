package net.jitl.common.entity.overworld;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HongoEntity extends Monster {

    public HongoEntity(EntityType<? extends HongoEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    public static boolean canSpawn(EntityType<? extends PathfinderMob> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                && worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
                && worldIn.getBiome(pos).getBiomeCategory() == Biome.BiomeCategory.MUSHROOM
                || worldIn.getBiome(pos).getBiomeCategory() == Biome.BiomeCategory.SWAMP;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (super.hurt(source, amount)) {
            if (random.nextInt(10) == 0) {
                if (source != DamageSource.OUT_OF_WORLD && source != DamageSource.MAGIC) {
                    JEffectCloudEntity poison = new JEffectCloudEntity(this, this.level, this.getX(), this.getY(), this.getZ(), 0.5F);
                    poison.excludeOwner();
                    poison.addPrimaryEffect(new MobEffectInstance(MobEffects.POISON, 500, 3));
                    poison.addPrimaryEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
                    poison.addSizeKey(10, 4);
                    poison.addSizeKey(200, 0);
                    poison.setColor(MobEffects.POISON.getColor());
                    poison.spawn();
                    level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                }
                return true;
            }
            if (source.getDirectEntity() instanceof WitherSkull) {
                WithershroomEntity withershroomEntity = new WithershroomEntity(JEntities.WITHERSHROOM_TYPE, this.level);
                withershroomEntity.setPos(this.getX(), this.getY(), this.getZ());
                level.addFreshEntity(withershroomEntity);
                this.remove(RemovalReason.DISCARDED);
            }
            return true;
        } else {
            return false;
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.55F;
    }

    protected SoundEvent getAmbientSound() {
        return JSounds.HONGO_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.HONGO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return JSounds.HONGO_DEATH.get();
    }

    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }
}
