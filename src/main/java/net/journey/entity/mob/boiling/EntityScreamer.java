package net.journey.entity.mob.boiling;

import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.init.JourneyLootTables;
import net.journey.init.items.JourneyItems;
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
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityScreamer extends JEntityMob {

    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.createKey(EntityScreamer.class, DataSerializers.BYTE);
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private int attackTimer;

    public EntityScreamer(World w) {
        super(w);
        this.experienceValue = 10;
        this.tasks.addTask(4, new EntityScreamer.AIFireballAttack());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.isImmuneToFire = true;
        setSize(0.7F, 1.5F);
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
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(10) == 0)
            dropItem(JourneyItems.boilingSkull, 1);
        if (rand.nextInt(3) == 0)
            dropItem(JourneyItems.boilPowder, 3);
        if (rand.nextInt(35) == 0)
            dropItem(JourneyItems.sizzlingEye, 1);
        super.dropFewItems(b, j);
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

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SCREAMER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.SCREAMER;
    }

    class AIFireballAttack extends EntityAIBase {
        private EntityScreamer field_179469_a = EntityScreamer.this;
        private int field_179467_b;
        private int field_179468_c;

        public AIFireballAttack() {
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public void startExecuting() {
            this.field_179467_b = 0;
        }

        @Override
        public void resetTask() {
            this.field_179469_a.setFlying(false);
        }

        @Override
        public void updateTask() {
            --this.field_179468_c;
            EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
            double d0 = this.field_179469_a.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.field_179468_c <= 0) {
                    this.field_179468_c = 20;
                    this.field_179469_a.attackEntityAsMob(entitylivingbase);
                }

                this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            } else if (d0 < 256.0D) {
                double d1 = entitylivingbase.posX - this.field_179469_a.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.field_179469_a.posY + this.field_179469_a.height / 2.0F);
                double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;

                if (this.field_179468_c <= 0) {
                    ++this.field_179467_b;

                    if (this.field_179467_b == 1) {
                        this.field_179468_c = 60;
                        this.field_179469_a.setFlying(true);
                    } else if (this.field_179467_b <= 4) {
                        this.field_179468_c = 6;
                    } else {
                        this.field_179468_c = 100;
                        this.field_179467_b = 0;
                        this.field_179469_a.setFlying(false);
                    }

                    if (this.field_179467_b > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.field_179469_a.world.playBroadcastSound(1009, new BlockPos((int) this.field_179469_a.posX, (int) this.field_179469_a.posY, (int) this.field_179469_a.posZ), 0);

                        for (int i = 0; i < 1; ++i) {
                            EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.field_179469_a.world, this.field_179469_a, d1 + this.field_179469_a.getRNG().nextGaussian() * f, d2, d3 + this.field_179469_a.getRNG().nextGaussian() * f);
                            entitysmallfireball.posY = this.field_179469_a.posY + this.field_179469_a.height / 2.0F + 0.5D;
                            this.field_179469_a.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }

                this.field_179469_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.field_179469_a.getNavigator().clearPath();
                this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }
    }
}