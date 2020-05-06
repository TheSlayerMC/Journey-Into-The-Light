package net.slayer.api.entity;

import net.journey.entity.MobStats;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityModMob extends EntityMob {

    public EntityModMob(World par1World) {
        super(par1World);
        setSize(1.0F, 2.0F);
    }

    @Override
    protected void initEntityAI() {
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

    protected void addAttackingAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.4D, false));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void addBasicAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(setFollowRange());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(setMovementSpeed());
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(setKnockbackResistance());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(setMaxHealth(new MobStats()));
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(setAttackDamage(new MobStats()));
    }

    public double setFollowRange() {
        return MobStats.normal_follow;
    }

    public double setMovementSpeed() {
        return MobStats.normalSpeed;
    }

    public double setKnockbackResistance() {
        return MobStats.knockBackResistance;
    }

    public abstract double setAttackDamage(MobStats s);

    public abstract double setMaxHealth(MobStats s);

    public abstract SoundEvent setLivingSound();

    public abstract SoundEvent setHurtSound();

    public abstract SoundEvent setDeathSound();

    public abstract Item getItemDropped();

    @Override
    protected Item getDropItem() {
        return getItemDropped();
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        for (int i = 0; i < 1 + rand.nextInt(1); i++)
            this.dropItem(getItemDropped(), 1);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

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
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
    }
}