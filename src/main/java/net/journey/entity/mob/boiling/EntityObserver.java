package net.journey.entity.mob.boiling;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.init.JourneyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityObserver extends JEntityMob {

    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.createKey(EntityObserver.class, DataSerializers.BYTE);
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private int attackTimer;

    public EntityObserver(World w) {
        super(w);
        this.experienceValue = 10;
        this.tasks.addTask(4, new EntityObserver.AIFireballAttack());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.isImmuneToFire = true;
        setSize(0.8F, 2.0F);
        this.setKnowledge(EnumKnowledgeType.BOIL, 5);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.OBSERVER_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.OBSERVER_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte) 0));
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.OBSERVER;
    }

    @Override
    public void onLivingUpdate() {

        if (this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }

        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
        for (Entity entity : e) {
            if (entity instanceof EntityPlayer && canEntityBeSeen(entity))
                entity.setFire(5 + rand.nextInt(7));
        }

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        if (this.world.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.AMBIENT, 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    protected void updateAITasks() {
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean isBurning() {
        return this.isFlying();
    }

    public boolean isFlying() {
        return (this.dataManager.get(ON_FIRE).byteValue() & 1) != 0;
    }

    public void setFlying(boolean b) {
        byte b0 = this.dataManager.get(ON_FIRE).byteValue();
        if (b) b0 = (byte) (b0 | 1);
        else b0 &= -2;
        this.dataManager.set(ON_FIRE, Byte.valueOf(b0));
    }

    class AIFireballAttack extends EntityAIBase {
        private final EntityObserver entity = EntityObserver.this;
        private int x;
        private int y;

        public AIFireballAttack() {
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public void startExecuting() {
            this.x = 0;
        }

        @Override
        public void resetTask() {
            this.entity.setFlying(false);
        }

        @Override
        public void updateTask() {
            --this.y;
            EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
            double d0 = this.entity.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.y <= 0) {
                    this.y = 20;
                    this.entity.attackEntityAsMob(entitylivingbase);
                }

                this.entity.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            } else if (d0 < 256.0D) {
                double d1 = entitylivingbase.posX - this.entity.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.entity.posY + this.entity.height / 2.0F);
                double d3 = entitylivingbase.posZ - this.entity.posZ;

                if (this.y <= 0) {
                    ++this.x;

                    if (this.x == 1) {
                        this.y = 60;
                        this.entity.setFlying(true);
                    } else if (this.x <= 4) {
                        this.y = 6;
                    } else {
                        this.y = 100;
                        this.x = 0;
                        this.entity.setFlying(false);
                    }

                    if (this.x > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.entity.world.playBroadcastSound(1009, new BlockPos((int) this.entity.posX, (int) this.entity.posY, (int) this.entity.posZ), 0);

                        for (int i = 0; i < 1; ++i) {
                            EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.entity.world, this.entity, d1 + this.entity.getRNG().nextGaussian() * f, d2, d3 + this.entity.getRNG().nextGaussian() * f);
                            entitysmallfireball.posY = this.entity.posY + this.entity.height / 2.0F + 0.5D;
                            this.entity.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }

                this.entity.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.entity.getNavigator().clearPath();
                this.entity.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }
    }
}