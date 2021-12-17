package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.jitl.JITL;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.tasks.FrozenTrollTasks;
import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

public class FrozenTrollEntity extends MonsterEntity {

    private final Inventory inventory = new Inventory(8);

    protected static final ImmutableList<SensorType<? extends Sensor<? super FrozenTrollEntity>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_ITEMS,
            SensorType.HURT_BY);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.LIVING_ENTITIES,
            MemoryModuleType.VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,
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
            MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM,
            MemoryModuleType.ATE_RECENTLY);


    public FrozenTrollEntity(EntityType<? extends FrozenTrollEntity> entityType, World world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
    }

    /*@Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }*/

    public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("frozen_wastes"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("dying_forest"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("bitterwood_forest"));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.inventory.fromTag(compound.getList("Inventory", 10));
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("frozenTrollBrain");
        this.getBrain().tick((ServerWorld) this.level, this);
        this.level.getProfiler().pop();
        FrozenTrollTasks.updateActivity(this);
        super.customServerAiStep();
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
    }

    protected int getExperienceReward(PlayerEntity player) {
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

    public boolean wantsToPickUp(ItemStack itemStack_) {
        return ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.canPickUpLoot() && FrozenTrollTasks.wantsToPickup(this, itemStack_);
    }

    protected void pickUpItem(ItemEntity itemEntity) {
        this.onItemPickup(itemEntity);
        FrozenTrollTasks.pickUpItem(this, itemEntity);
    }

    public void holdInOffHand(ItemStack itemStack_) {
        if (itemStack_.getItem() == FrozenTrollTasks.BARTERING_ITEM) {
            this.setItemSlot(EquipmentSlotType.OFFHAND, itemStack_);
            this.setGuaranteedDrop(EquipmentSlotType.OFFHAND);
        } else {
            this.setItemSlotAndDropWhenKilled(EquipmentSlotType.OFFHAND, itemStack_);
        }
    }

    public ItemStack addToInventory(ItemStack itemStack_) {
        return this.inventory.addItem(itemStack_);
    }

    public boolean canAddToInventory(ItemStack itemStack_) {
        return this.inventory.canAddItem(itemStack_);
    }

    @Override
    protected Brain.@NotNull BrainCodec<FrozenTrollEntity> brainProvider() {
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
    public ActionResultType mobInteract(PlayerEntity playerEntity_, Hand hand_) {
        ActionResultType actionresulttype = super.mobInteract(playerEntity_, hand_);
        if (actionresulttype.consumesAction()) {
            return actionresulttype;
        } else if (!this.level.isClientSide) {
            return FrozenTrollTasks.mobInteract(this, playerEntity_, hand_);
        } else {
            boolean flag = FrozenTrollTasks.canAdmire(this, playerEntity_.getItemInHand(hand_)) && this.getArmPose() != JEntityAction.ADMIRING_ITEM;
            return flag ? ActionResultType.SUCCESS : ActionResultType.PASS;
        }
    }

    public JEntityAction getArmPose() {
        if (FrozenTrollTasks.isLovedItem(this.getOffhandItem().getItem())) {
            return JEntityAction.ADMIRING_ITEM;
        } else {
            return JEntityAction.DEFAULT;
        }
    }

    protected SoundEvent getAmbientSound() {
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
