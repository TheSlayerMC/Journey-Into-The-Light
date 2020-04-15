package net.slayer.api.entity;

import com.google.common.base.Optional;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class EntityModTameable extends EntityTameable {

    protected static final DataParameter<Byte> TAMED = EntityDataManager.createKey(EntityModTameable.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(EntityModTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected EntityAISit aiSit;

    public EntityModTameable(World w) {
        super(w);
        this.aiSit = new EntityAISit(this);
        addBasicAI();
    }

    public double getHP() {
        return getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
    }

    public double getMoveSpeed() {
        return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    public double getAttackDamage() {
        return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
    }

    public double getFollowRange() {
        return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
    }

    public double getKnockbackResistance() {
        return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }

    public abstract SoundEvent setLivingSound();

    public abstract SoundEvent setHurtSound();

    public abstract SoundEvent setDeathSound();

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return setLivingSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        super.getHurtSound(d);
        return setHurtSound();
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return setDeathSound();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TAMED, Byte.valueOf((byte) 0));
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.getOwnerId() == null) {
            compound.setString("OwnerUUID", "");
        } else {
            compound.setString("OwnerUUID", this.getOwnerId().toString());
        }

        compound.setBoolean("Sitting", this.isSitting());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        String s;

        if (compound.hasKey("OwnerUUID", 8)) {
            s = compound.getString("OwnerUUID");
        } else {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }

        if (!s.isEmpty()) {
            try {
                this.setOwnerId(UUID.fromString(s));
                this.setTamed(true);
            } catch (Throwable var4) {
                this.setTamed(false);
            }
        }

        if (this.aiSit != null) {
            this.aiSit.setSitting(compound.getBoolean("Sitting"));
        }

        this.setSitting(compound.getBoolean("Sitting"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 7) {
            this.playTameEffect(true);
        } else if (id == 6) {
            this.playTameEffect(false);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public boolean isTamed() {
        return (this.dataManager.get(TAMED).byteValue() & 4) != 0;
    }

    @Override
    public void setTamed(boolean tamed) {
        byte b0 = this.dataManager.get(TAMED).byteValue();

        if (tamed) {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 & -5)));
        }

        this.setupTamedAI();
    }

    protected void setupTamedAI() {
    }

    @Override
    public boolean isSitting() {
        return (this.dataManager.get(TAMED).byteValue() & 1) != 0;
    }

    @Override
    public void setSitting(boolean sitting) {
        byte b0 = this.dataManager.get(TAMED).byteValue();

        if (sitting) {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    @Override
    @Nullable
    public UUID getOwnerId() {
        return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }

    @Override
    public void setOwnerId(@Nullable UUID p_184754_1_) {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
    }

    @Override
    public void setTamedBy(EntityPlayer player) {
        this.setTamed(true);
        this.setOwnerId(player.getUniqueID());

        if (player instanceof EntityPlayerMP) {
            CriteriaTriggers.TAME_ANIMAL.trigger((EntityPlayerMP) player, this);
        }
    }

    @Override
    @Nullable
    public EntityLivingBase getOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public boolean isOwner(EntityLivingBase entityIn) {
        return entityIn == this.getOwner();
    }

    @Override
    public EntityAISit getAISit() {
        return this.aiSit;
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        return true;
    }

    @Override
    public Team getTeam() {
        if (this.isTamed()) {
            EntityLivingBase entitylivingbase = this.getOwner();

            if (entitylivingbase != null) {
                return entitylivingbase.getTeam();
            }
        }

        return super.getTeam();
    }

    @Override
    public boolean isOnSameTeam(Entity entityIn) {
        if (this.isTamed()) {
            EntityLivingBase entitylivingbase = this.getOwner();

            if (entityIn == entitylivingbase) {
                return true;
            }

            if (entitylivingbase != null) {
                return entitylivingbase.isOnSameTeam(entityIn);
            }
        }

        return super.isOnSameTeam(entityIn);
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (!this.world.isRemote && this.world.getGameRules().getBoolean("showDeathMessages") && this.getOwner() instanceof EntityPlayerMP) {
            this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage());
        }
        super.onDeath(cause);
    }

    protected void addBasicAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        //this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0F, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0F, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
    }

    protected void addAttackingAI() {
        //this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }
}