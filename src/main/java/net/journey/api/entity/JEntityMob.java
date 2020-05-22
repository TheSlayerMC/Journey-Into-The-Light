package net.journey.api.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class JEntityMob extends EntityMob implements ISettingsConsumer {

	public JEntityMob(World world) {
		super(world);
		setSize(1.0F, 2.0F);
	}

	@Override
	protected void initEntityAI() {
		//Basic tasks
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	protected void addMeleeAttackingAI() {
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.4D, false));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntitySettings entitySettings = getEntitySettings();
		entitySettings.getAttributes().forEach((attribute, value) -> {
			IAttributeInstance instance = getEntityAttribute(attribute);

			//noinspection ConstantConditions
			if (instance == null) {
				instance = getAttributeMap().registerAttribute(attribute);
			}

			instance.setBaseValue(value);
		});
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	/**
	 * Living sound
	 */
	@Override
	protected abstract SoundEvent getAmbientSound();

	@Override
	protected abstract SoundEvent getHurtSound(DamageSource d);

	@Override
	protected abstract SoundEvent getDeathSound();

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

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}
}