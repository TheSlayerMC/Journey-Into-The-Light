package net.jitl.common.entity.overworld;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.init.JSounds;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;

public class WithershroomEntity extends Monster {

    public boolean canGenerateRose;

    public WithershroomEntity(EntityType<? extends WithershroomEntity> entityType, Level world) {
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
    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            if (tickCount % 600 == 0) { //for every 600 ticks, we check surrounding entities for creepers
                List<Creeper> entitiesNear = this.level.getEntitiesOfClass(Creeper.class, this.getBoundingBox().inflate(4D));
                if (!entitiesNear.isEmpty()) {
                    spawnEffectCloud(); //if a creeper is nearby, we spawn an effect cloud, and set canGenerateRose to true
                }
            }
            if (this.getBlockStateOn().getBlock() instanceof GrassBlock) { //if the block the entity is standing on is valid, replace it with coarse dirt
                level.setBlock(this.blockPosition().below(), Blocks.COARSE_DIRT.defaultBlockState(), 1);
            }
        }
        if (canGenerateRose) { //if true, we replace all 1 block tall flowers in the area with wither roses
            replaceWithWitherRose();
            canGenerateRose = false; //set it back to false to prevent constant rose generation
        }
        super.tick();
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof WitherSkull) {
            return false;
        } else if (super.hurt(source, amount) && random.nextInt(10) == 0) {
            if (source != DamageSource.OUT_OF_WORLD && source != DamageSource.MAGIC) {
                spawnEffectCloud();
            }
            return true;
        } else {
            return false;
        }
    }

    public void spawnEffectCloud() {
        canGenerateRose = true;
        JEffectCloudEntity poison = new JEffectCloudEntity(this, this.level, this.getX(), this.getY(), this.getZ(), 0.5F);
        poison.excludeOwner();
        poison.addPrimaryEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
        poison.addPrimaryEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
        poison.addSizeKey(10, 4);
        poison.addSizeKey(200, 0);
        poison.setColor(MobEffects.WITHER.getColor());
        poison.spawn();
        level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    public void replaceWithWitherRose() {
        Stream<BlockPos> blockPosStream = BlockPos.betweenClosedStream(this.getBoundingBox().inflate(2D));
        blockPosStream.forEach((pos) -> {
            if (level.getBlockState(pos).getBlock() instanceof FlowerBlock) {
                level.setBlockAndUpdate(pos, Blocks.WITHER_ROSE.defaultBlockState());
            }
        });
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean canBeAffected(MobEffectInstance potioneffectIn) {
        return potioneffectIn.getEffect() != MobEffects.WITHER && super.canBeAffected(potioneffectIn);
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

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("generateRoses", canGenerateRose);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("generateRoses")) {
            canGenerateRose = compound.getBoolean("generateRoses");
        }
    }
}
