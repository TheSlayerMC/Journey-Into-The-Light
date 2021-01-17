package net.jitl.common.entity.overworld;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;

import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HongoEntity extends CreatureEntity {

    public HongoEntity(EntityType<? extends HongoEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                && worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
                && worldIn.getBiome(pos).getBiomeCategory() == Biome.Category.MUSHROOM
                || worldIn.getBiome(pos).getBiomeCategory() == Biome.Category.SWAMP;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (super.hurt(source, amount) && random.nextInt(10) == 0) {
            if (source != DamageSource.OUT_OF_WORLD && source != DamageSource.MAGIC) {
    			JEffectCloudEntity poison = new JEffectCloudEntity(this, this.level, this.getX(), this.getY(), this.getZ(), 0.5F);
    			poison.treatOwnerAsException();
    			poison.addPrimaryEffect(new EffectInstance(Effects.POISON, 500, 3));
    			poison.addPrimaryEffect(new EffectInstance(Effects.CONFUSION, 200));
    			poison.addSizeKey(10, 4);
    			poison.addSizeKey(200, 0);
    			poison.setColor(Effects.POISON.getColor());
    			poison.spawn();
                /*AreaEffectCloudEntity poison = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
                poison.setRadius(0.5F); //the base radius. Vanilla starts large and shrinks down, but I think the opposite is more realistic
                poison.setWaitTime(5); //time before the cloud starts growing/damaging. Might be worth decreasing since player can run away anyways
                poison.setRadiusPerTick(0.15F); //the speed at which the cloud size changes. Set negative if you want it to shrink like in vanilla
                poison.setOwner(this); //does nothing to my knowledge
                poison.setDuration(20); //how long the cloud lasts
                poison.setPotion(new Potion(new EffectInstance(Effects.POISON, 500, 3)));
                poison.addEffect(new EffectInstance(Effects.CONFUSION, 200));
                level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
                this.level.addFreshEntity(poison);*/
            }
    		return true;
    	} else {
    		return false;
    	}
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
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
