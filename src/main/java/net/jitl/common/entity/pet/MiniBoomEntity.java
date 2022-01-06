package net.jitl.common.entity.pet;

import net.jitl.common.entity.goal.MiniBoomSwellGoal;
import net.jitl.common.entity.overworld.WithershroomEntity;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JSounds;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PowerableMob;

public class MiniBoomEntity extends TamableAnimal implements PowerableMob {

    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR;
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED;
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED;
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 3;
    private int cooldownTimer;
    private int cooldownReset = 100;

    static {
        DATA_SWELL_DIR = SynchedEntityData.defineId(MiniBoomEntity.class, EntityDataSerializers.INT);
        DATA_IS_POWERED = SynchedEntityData.defineId(MiniBoomEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_IS_IGNITED = SynchedEntityData.defineId(MiniBoomEntity.class, EntityDataSerializers.BOOLEAN);
    }

    public MiniBoomEntity(EntityType<? extends MiniBoomEntity> entity, Level world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Monster.class, true));
        this.goalSelector.addGoal(2, new MiniBoomSwellGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public int getMaxFallDistance() {
        return this.getTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }

    @Override
    public boolean causeFallDamage(float f, float f1) {
        boolean flag = super.causeFallDamage(f, f1);
        this.swell = (int)((float)this.swell + f * 1.5F);
        if (this.swell > this.maxSwell - 5) {
            this.swell = this.maxSwell - 5;
        }

        return flag;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if ((Boolean)this.entityData.get(DATA_IS_POWERED)) {
            nbt.putBoolean("powered", true);
        }
        nbt.putInt("Cooldown", (int)this.cooldownTimer);
        nbt.putInt("CooldownReset", (int)this.cooldownReset);
        nbt.putShort("Fuse", (short)this.maxSwell);
        nbt.putByte("ExplosionRadius", (byte)this.explosionRadius);
        nbt.putBoolean("ignited", this.isIgnited());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(DATA_IS_POWERED, nbt.getBoolean("powered"));

        if(nbt.contains("Cooldown", 99))
            this.cooldownTimer = nbt.getInt("Cooldown");

        if(nbt.contains("CooldownReset", 99))
            this.cooldownTimer = nbt.getInt("CooldownReset");

        if(nbt.contains("Fuse", 99))
            this.maxSwell = nbt.getShort("Fuse");

        if(nbt.contains("ExplosionRadius", 99))
            this.explosionRadius = nbt.getByte("ExplosionRadius");

        if(nbt.getBoolean("ignited"))
            this.ignite();
    }

    @Override
    public void tick() {
        if(this.isAlive()) {
            this.oldSwell = this.swell;
            if(this.isIgnited()) this.setSwellDir(1);
            int i = this.getSwellDir();
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Monster.class, true));

            if(i > 0 && this.swell == 0)
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);

            if(this.cooldownTimer >= 0)
                this.swell += i;

            if(this.swell < 0) this.swell = 0;
            this.cooldownTimer--;

            if(this.cooldownTimer < 0) this.cooldownTimer = 0;

                 if(this.swell >= this.maxSwell) {
                     if(cooldownTimer <= 0) {
                        this.swell = 0;
                        this.explode();
                        setTarget((LivingEntity) null);
                        this.targetSelector.removeGoal(new NearestAttackableTargetGoal(this, Monster.class, true));
                        this.cooldownTimer = this.cooldownReset;
                 }
            }
        }
        super.tick();
    }

    @Override
    public boolean doHurtTarget(Entity e) {
        return true;
    }

    public boolean isPowered() {
        return (Boolean)this.entityData.get(DATA_IS_POWERED);
    }

    @OnlyIn(Dist.CLIENT)
    public float getSwelling(float s) {
        return Mth.lerp(s, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    public int getSwellDir() {
        return (Integer)this.entityData.get(DATA_SWELL_DIR);
    }

    @Override
    public void thunderHit(ServerLevel server, LightningBolt lightning) {
        super.thunderHit(server, lightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    public void setSwellDir(int dir) {
        this.entityData.set(DATA_SWELL_DIR, dir);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        super.hurt(source, amount);
        if(source.isExplosion()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(isOwnedBy(player)) {
            this.setOrderedToSit(!this.isOrderedToSit());
        }

        if(itemstack.getItem() == Items.FLINT_AND_STEEL) {
            if(!this.level.isClientSide) {

                if(!this.isTame()){
                    tame(player);
                }

                itemstack.hurtAndBreak(1, player, (world) -> {
                    world.broadcastBreakEvent(hand);
                });
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void explode() {
        if(!this.level.isClientSide) {
            float multiplier = this.isPowered() ? 2.0F : 1.0F;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * multiplier, Explosion.BlockInteraction.NONE);
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloudentity.setRadius(2.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
            Iterator var3 = collection.iterator();
            while(var3.hasNext()) {
                MobEffectInstance effectinstance = (MobEffectInstance)var3.next();
                areaeffectcloudentity.addEffect(new MobEffectInstance(effectinstance));
            }
            this.level.addFreshEntity(areaeffectcloudentity);
        }

    }

    public boolean isIgnited() {
        return (Boolean)this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }

    @Nullable
    @Override
    public AgableMob getBreedOffspring(ServerLevel serverWorld, AgableMob ageableEntity) {
        return null;
    }
}
