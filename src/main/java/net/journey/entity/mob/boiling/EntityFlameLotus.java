package net.journey.entity.mob.boiling;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFlameLotus extends JEntityMob {

	public EntityFlameLotus(World par1World) {
		super(par1World);
		this.setSize(2.0F, 0.4F);
		this.setKnowledge(EnumKnowledgeType.BOIL, 1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.FLAME_LOTUS_HEALTH);
		EntityAttributesHelper.setMovementSpeed(this, MobStats.FLAME_LOTUS_MOVEMENT_SPEED);
		EntityAttributesHelper.setKnockbackResistance(this, MobStats.FLAME_LOTUS_KNOCKBACK_RESISTANCE);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAILookIdle(this));
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		double d0 = this.posX - entityIn.posX;
		double d1 = this.posZ - entityIn.posZ;
		double d2 = MathHelper.absMax(d0, d1);
		if (d2 >= 0.009999999776482582D) {
			d2 = MathHelper.sqrt(d2);
			d0 = d0 / d2;
			d1 = d1 / d2;
			double d3 = 1.0D / d2;
			if (d3 > 1.0D) {
				d3 = 1.0D;
			}
			d0 = d0 * d3;
			d1 = d1 * d3;
			d0 = d0 * 0.05D;
			d1 = d1 * 0.05D;
			d0 = d0 * (double) (1.0F - entityIn.entityCollisionReduction);
			d1 = d1 * (double) (1.0F - entityIn.entityCollisionReduction);
			entityIn.addVelocity(-d0, 0.0D, -d1);
		}
	}

	@Override
	public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
		if (this.getHealth() < 0.0F) {
			super.knockBack(entity, strength, xRatio, zRatio);
		}
	}

	@Override
	public int getVerticalFaceSpeed() {
		return 0;
	}

	@Override
	public int getHorizontalFaceSpeed() {
		return 0;
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.FLAME_LOTUS;
	}
}