package net.jitl.common.entity.frozen;

import net.jitl.JITL;
import net.jitl.init.JEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class ShiveringRamEntity extends AnimalEntity implements IShearable, IForgeShearable {
    private static final DataParameter<Byte> DATA_WOOL_ID = EntityDataManager.defineId(SheepEntity.class, DataSerializers.BYTE);

    private int eatAnimationTick;
    private EatGrassGoal eatBlockGoal;

    public ShiveringRamEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.eatBlockGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
        return 0.02F;
    }

    @Override
    protected void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }

        super.aiStep();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WOOL_ID, (byte) 0);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadEatPositionScale(float float_) {
        if (this.eatAnimationTick <= 0) {
            return 0.0F;
        } else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
            return 1.0F;
        } else {
            return this.eatAnimationTick < 4 ? ((float) this.eatAnimationTick - float_) / 4.0F : -((float) (this.eatAnimationTick - 40) - float_) / 4.0F;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadEatAngleScale(float float_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float) (this.eatAnimationTick - 4) - float_) / 32.0F;
            return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float) Math.PI / 5F) : this.xRot * ((float) Math.PI / 180F);
        }
    }


    @Override
    public ActionResultType mobInteract(PlayerEntity playerEntity_, Hand hand_) {
        ItemStack itemstack = playerEntity_.getItemInHand(hand_);
        if (itemstack.getItem() == Items.SHEARS) { //Forge: Moved to onSheared
            if (!this.level.isClientSide && this.readyForShearing()) {
                this.shear(SoundCategory.PLAYERS);
                itemstack.hurtAndBreak(1, playerEntity_, (playerEntity1_) -> {
                    playerEntity1_.broadcastBreakEvent(hand_);
                });
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.CONSUME;
            }
        } else {
            return super.mobInteract(playerEntity_, hand_);
        }
    }

    @Override
    public void shear(SoundCategory category) {
        this.level.playSound(null, this, SoundEvents.SHEEP_SHEAR, category, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for (int j = 0; j < i; ++j) {
            ItemEntity itementity = this.spawnAtLocation(Blocks.LIGHT_BLUE_WOOL, 1);
            if (itementity != null) {
                itementity.setDeltaMovement(itementity.getDeltaMovement().add(
                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F,
                        this.random.nextFloat() * 0.05F,
                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
            }
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sheared", this.isSheared());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    public boolean isSheared() {
        return (this.entityData.get(DATA_WOOL_ID) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = this.entityData.get(DATA_WOOL_ID);
        if (sheared) {
            this.entityData.set(DATA_WOOL_ID, (byte) (b0 | 16));
        } else {
            this.entityData.set(DATA_WOOL_ID, (byte) (b0 & -17));
        }

    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("frozen_wastes"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("dying_forest"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("bitterwood_forest"));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return SoundEvents.SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    public ShiveringRamEntity getBreedOffspring(@NotNull ServerWorld serverWorld_, @NotNull AgeableEntity ageableEntity_) {
        return JEntities.SHIVERING_RAM_TYPE.create(serverWorld_);
    }
}
