package net.jitl.common.entity.frozen;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.tile.PedestalTile;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JItems;
import net.jitl.core.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FrozenGuardianEntity extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> DATA_IS_ACTIVATED = SynchedEntityData.defineId(FrozenGuardianEntity.class, EntityDataSerializers.BOOLEAN);

    private int death_timer;

    public FrozenGuardianEntity(EntityType<? extends FrozenGuardianEntity> entityType, Level world) {
        super(entityType, world);
        this.death_timer = 50;
    }

    @Override
    protected void registerGoals() { }

    public static boolean canSpawn(EntityType<? extends PathfinderMob> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficultyIn) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JItems.STAFF_OF_CONJURING));
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            this.death_timer--;
            this.level.addParticle(ParticleTypes.CLOUD,
                    this.getX() - 0.5D + random.nextDouble(),
                    this.getY() + 0.5D + random.nextDouble(),
                    this.getZ() - 0.5D + random.nextDouble(),
                    this.random.nextGaussian() * 0.05D,
                    0.15D,
                    this.random.nextGaussian() * 0.05D);
            if (death_timer <= 0) {
                for (int i = 0; i < 24; ++i) {
                    this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            this.getX() - Mth.nextDouble(random, -0.45D, 0.75D),
                            this.getY() + Mth.nextDouble(random, 0.5D, 2.0D),
                            this.getZ() - Mth.nextDouble(random, -0.45D, 0.75D),
                            this.random.nextGaussian() * 0.05D,
                            0.15D,
                            this.random.nextGaussian() * 0.05D);
                }
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
                this.remove(RemovalReason.DISCARDED);
                
                if (!level.isClientSide) {
                    this.level.addFreshEntity(new ItemEntity(level, this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.STAFF_OF_CONJURING, 1)));
                }
                this.death_timer = 100;
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_ACTIVATED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("activated", this.entityData.get(DATA_IS_ACTIVATED));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setActivated(compound.getBoolean("activated"));
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void push(Entity entity) { }

    @Override
    protected void doPush(Entity entityIn) { }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        int check_radius = 8;
        int totalPedestals = 0;
        final Level world = this.level;
        final BlockPos entityPos = new BlockPos(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.FROZEN_PEDESTAL) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if (tile != null && tile.getItem(0).getItem().equals(JItems.FROSTBORN_SOUL)) {
                            if (isActivated()) {
                                if (!level.isClientSide) {
                                    summonLightning(pos);
                                    disableFrozenBlizzard();
                                }
                                tile.setItem(0, ItemStack.EMPTY);
                            }
                            totalPedestals++;
                        }
                    }
                }
            }
        }
        if (totalPedestals >= 8) {
            this.playSound(JSounds.FROZEN_GUARDIAN_DEATH.get(), 1.5F, 1.0F);
            setActivated(true);
        }
        return super.mobInteract(playerEntity, hand);
    }

    public void summonLightning(BlockPos pos) {
        EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);
        bolt.setPos(pos.getX(), pos.getY() + 1.2, pos.getZ());
        bolt.setARGB(0x5acbff);
        bolt.setVisualOnly(true);
        if(!level.isClientSide)
            this.level.addFreshEntity(bolt);
    }

    public void disableFrozenBlizzard() {
        int playerArea = 10;
        AABB axisalignedbb = AABB.unitCubeFromLowerCorner(this.position()).inflate(playerArea);
        for (Player player : this.level.getEntitiesOfClass(Player.class, axisalignedbb)) {
            JPlayer capability = JPlayer.from(player);
            if (capability != null) {
                capability.frozenBlizzard.setBlizzardDisabled(true);
                capability.detectAndSendChanges();
            }
        }
    }
}
