package net.slayer.api.entity;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class EntityModFlying extends EntityFlying {

	public EntityModFlying(World w) {
		super(w);
		setSize(0.5F, 0.5F);
	}
	
	public double getHP(){return getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();}
	public double getMoveSpeed(){return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();}
	public double getAttackDamage(){return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();}
	public double getFollowRange(){return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();}
	public double getKnockbackResistance(){return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(setFollowRange());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(setMovementSpeed());
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(setKnockbackResistance());
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(setMaxHealth(new MobStats()));
	}

	public double setFollowRange(){return MobStats.follow;}
	public double setMovementSpeed(){return MobStats.normalSpeed;}
	public double setKnockbackResistance() {return MobStats.knockBackResistance;}

	public abstract double setMaxHealth(MobStats s);

	@Override
	protected void dropFewItems(boolean b, int j) {
		for(int i = 0; i < 1 + rand.nextInt(1); i++)
			this.dropItem(getDropItem(), 1);
	}
}