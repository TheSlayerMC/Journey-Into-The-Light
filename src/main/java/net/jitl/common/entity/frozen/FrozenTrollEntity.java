package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.tasks.FrozenTrollTasks;
import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.jitl.core.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.Brain.Provider;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

public class FrozenTrollEntity extends Monster {

    private static final EntityDataAccessor<Boolean> IS_ANGRY_ID = SynchedEntityData.defineId(FrozenTrollEntity.class, EntityDataSerializers.BOOLEAN);

    private final SimpleContainer inventory = new SimpleContainer(8);

    protected static final ImmutableList<SensorType<? extends Sensor<? super FrozenTrollEntity>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_ITEMS,
            SensorType.HURT_BY);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN,
            MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.ANGRY_AT,
            MemoryModuleType.UNIVERSAL_ANGER,
            MemoryModuleType.AVOID_TARGET,
            MemoryModuleType.ADMIRING_ITEM,
            MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM,
            MemoryModuleType.ADMIRING_DISABLED,
            MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM,
            MemoryModuleType.NEAREST_VISIBLE_NEMESIS,
            MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM
    );


    public FrozenTrollEntity(EntityType<? extends FrozenTrollEntity> entityType, Level world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
    }

    public static boolean canSpawn(EntityType<? extends PathfinderMob> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("frozen_wastes"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("dying_forest"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("bitterwood_forest"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("Inventory", this.inventory.createTag());
        compound.putBoolean("angry", this.entityData.get(IS_ANGRY_ID));
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.inventory.fromTag(compound.getList("Inventory", 10));
        setAngry(compound.getBoolean("angry"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ANGRY_ID, false);
    }

    public boolean isAngry() {
        return this.entityData.get(IS_ANGRY_ID);
    }

    public void setAngry(boolean angry) {
        this.entityData.set(IS_ANGRY_ID, angry);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("frozenTrollBrain");
        this.getBrain().tick((ServerLevel) this.level, this);
        this.level.getProfiler().pop();
        FrozenTrollTasks.updateActivity(this);

        boolean isPresent = getTarget() != null;
        this.entityData.set(IS_ANGRY_ID, isPresent);

        super.customServerAiStep();
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
    }

    protected int getExperienceReward(Player player) {
        return this.xpReward;
    }

    public void playSound(SoundEvent soundEvent_) {
        this.playSound(soundEvent_, this.getSoundVolume(), this.getVoicePitch());
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entityIn) {
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof LivingEntity) {
                //entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(-MathHelper.sin((float) (this.lerpYRot * Math.PI / 180.0F)) * 2, 0.1D, MathHelper.cos((float) (this.lerpYRot * Math.PI / 180.0F)) * 2));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean wantsToPickUp(ItemStack itemStack_) {
        return ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.canPickUpLoot() && FrozenTrollTasks.wantsToPickup(this, itemStack_);
    }

    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        this.onItemPickup(itemEntity);
        FrozenTrollTasks.pickUpItem(this, itemEntity);
    }

    public void holdInOffHand(ItemStack itemStack_) {
        if (itemStack_.getItem() == FrozenTrollTasks.BARTERING_ITEM) {
            this.setItemSlot(EquipmentSlot.OFFHAND, itemStack_);
            this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
        } else {
            this.setItemSlotAndDropWhenKilled(EquipmentSlot.OFFHAND, itemStack_);
        }
    }

    public ItemStack addToInventory(ItemStack itemStack_) {
        return this.inventory.addItem(itemStack_);
    }

    public boolean canAddToInventory(ItemStack itemStack_) {
        return this.inventory.canAddItem(itemStack_);
    }

    @Override
    protected @NotNull Provider<FrozenTrollEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    @Override
    protected @NotNull Brain<?> makeBrain(@NotNull Dynamic<?> dynamicIn) {
        return FrozenTrollTasks.makeBrain(this, this.brainProvider().makeBrain(dynamicIn));
    }

    @Override
    public Brain<FrozenTrollEntity> getBrain() {
        return (Brain<FrozenTrollEntity>) super.getBrain();
    }

    @Override
    public InteractionResult mobInteract(Player playerEntity_, InteractionHand hand_) {
        InteractionResult actionresulttype = super.mobInteract(playerEntity_, hand_);
        if (actionresulttype.consumesAction()) {
            return actionresulttype;
        } else if (!this.level.isClientSide) {
            return FrozenTrollTasks.mobInteract(this, playerEntity_, hand_);
        } else {
            boolean flag = FrozenTrollTasks.canAdmire(this, playerEntity_.getItemInHand(hand_)) && this.getArmPose() != JEntityAction.ADMIRING_ITEM;
            return flag ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
    }

    public JEntityAction getArmPose() {
        if (this.getOffhandItem().is(JItems.RIMESTONE)) { //FIXME not detecting offhand item?
            return JEntityAction.ADMIRING_ITEM;
        } else {
            return JEntityAction.DEFAULT;
        }
    }

    @Override
    public void playAmbientSound() {
        if (this.isAngry()) {
            this.playSound(getAngryAmbientSound(), this.getSoundVolume(), this.getVoicePitch());
        } else {
            this.playSound(getCuteAmbientSound(), this.getSoundVolume(), this.getVoicePitch() + 1.0F);
        }
    }

    protected SoundEvent getCuteAmbientSound() {
        return JSounds.FROZEN_TROLL_INTRIGUED.get();
    }

    protected SoundEvent getAngryAmbientSound() {
        return JSounds.FROZEN_TROLL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.FROZEN_TROLL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return JSounds.FROZEN_TROLL_DEATH.get();
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }
}
