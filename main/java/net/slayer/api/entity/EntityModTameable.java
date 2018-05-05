package net.slayer.api.entity;

import net.journey.enums.EnumSounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class EntityModTameable extends EntityTameable {

	public EntityModTameable(World w) {
		super(w);
		addBasicAI();
	}

	public double getHP(){return getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();}
	public double getMoveSpeed(){return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();}
	public double getAttackDamage(){return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();}
	public double getFollowRange(){return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();}
	public double getKnockbackResistance(){return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();}

	public abstract EnumSounds setLivingSound();
	public abstract EnumSounds setHurtSound();
	public abstract EnumSounds setDeathSound();

	@Override
	protected SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return setLivingSound().getNonPrefixedName();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		super.getHurtSound(d);
		return setHurtSound().getNonPrefixedName();
	}

	@Override
	protected SoundEvent getDeathSound() {
		super.getDeathSound();
		return setDeathSound().getNonPrefixedName();
	}

	protected void mountPlayer(EntityPlayer player){

	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(!this.world.isRemote && !this.hasPath() && this.onGround) {
			this.world.setEntityState(this, (byte)8);
		}
		if(!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
			this.setAngry(false);
		}
	}

	public void setAngry(boolean angry)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (angry)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
		}
	}

	public boolean isAngry()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	protected void addBasicAI(){
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0F, true));
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

	protected void addAttackingAI(){
		this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
		this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}
}