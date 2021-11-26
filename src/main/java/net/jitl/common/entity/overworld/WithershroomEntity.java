package net.jitl.common.entity.overworld;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class WithershroomEntity extends MonsterEntity {

    public boolean canGenerateRose;

    public WithershroomEntity(EntityType<? extends WithershroomEntity> entityType, World world) {
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
    public @NotNull CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            if (tickCount % 600 == 0) { //for every 600 ticks, we check surrounding entities for creepers
                List<LivingEntity> entitiesNear = this.level.getEntitiesOfClass(CreeperEntity.class, this.getBoundingBox().inflate(4D));
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
        if (entity instanceof WitherSkullEntity) {
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
        poison.addPrimaryEffect(new EffectInstance(Effects.WITHER, 60, 1));
        poison.addPrimaryEffect(new EffectInstance(Effects.CONFUSION, 200));
        poison.addSizeKey(10, 4);
        poison.addSizeKey(200, 0);
        poison.setColor(Effects.WITHER.getColor());
        poison.spawn();
        level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
    }

    public void replaceWithWitherRose() {
        Stream<BlockPos> blockPosStream = BlockPos.betweenClosedStream(this.getBoundingBox().inflate(2D));
        blockPosStream.forEach((pos) -> {
            if (level.getBlockState(pos).getBlock() instanceof FlowerBlock) {
                level.setBlockAndUpdate(pos, Blocks.WITHER_ROSE.defaultBlockState());
            }
        });
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean addEffect(EffectInstance effectInstanceIn) {
        return false;
    }

    @Override
    public boolean canBeAffected(EffectInstance potioneffectIn) {
        return potioneffectIn.getEffect() != Effects.WITHER && super.canBeAffected(potioneffectIn);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("generateRoses", canGenerateRose);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("generateRoses")) {
            canGenerateRose = compound.getBoolean("generateRoses");
        }
    }
}
