package net.jitl.common.entity.frozen;

import net.jitl.JITL;
import net.jitl.init.JEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class CapybaraEntity extends TameableEntity {
    private static final DataParameter<Optional<UUID>> DATA_ID_OWNER_UUID = EntityDataManager.defineId(CapybaraEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(
            Items.WHEAT,
            Items.MELON,
            Items.MELON_SEEDS,
            Items.BEETROOT_SEEDS,
            Items.WHEAT_SEEDS,
            Items.PUMPKIN_SEEDS,
            Blocks.PUMPKIN.asItem(),
            Blocks.HAY_BLOCK.asItem(),
            Items.APPLE,
            Items.POTATO,
            Items.CARROT,
            Items.GOLDEN_CARROT,
            Items.GOLDEN_APPLE,
            Items.ENCHANTED_GOLDEN_APPLE);

    public CapybaraEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER)
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("frozen_wastes"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("dying_forest"))
                || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("bitterwood_forest"));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
        return 0.02F;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld_, AgeableEntity ageableEntity_) {
        return JEntities.CAPYBARA_TYPE.create(serverWorld_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, AbstractHorseEntity.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_OWNER_UUID, Optional.empty());
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_ID_OWNER_UUID).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID uniqueId) {
        this.entityData.set(DATA_ID_OWNER_UUID, Optional.ofNullable(uniqueId));
    }
}
