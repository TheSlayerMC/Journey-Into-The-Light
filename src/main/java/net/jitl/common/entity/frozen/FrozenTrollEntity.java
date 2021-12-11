package net.jitl.common.entity.frozen;

import net.jitl.init.JBiomeRegistry;
import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FrozenTrollEntity extends MonsterEntity {

    public FrozenTrollEntity(EntityType<? extends FrozenTrollEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                || worldIn.getBiome(pos).getBiomeCategory() == JBiomeRegistry.FROZEN_WASTES.getBiomeCategory()
                || worldIn.getBiome(pos).getBiomeCategory() == JBiomeRegistry.FROZEN_DYING_FOREST.getBiomeCategory()
                || worldIn.getBiome(pos).getBiomeCategory() == JBiomeRegistry.FROZEN_BITTERWOOD_FOREST.getBiomeCategory();
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if(super.doHurtTarget(entityIn)) {
            if(entityIn instanceof LivingEntity) {
                entityIn.setDeltaMovement(entityIn.getDeltaMovement().add((float) (-MathHelper.sin((float) (this.lerpYRot * Math.PI / 180.0F))) * 2, 0.1D, (float) (MathHelper.cos((float) (this.lerpYRot * Math.PI / 180.0F))) * 2));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }
}
