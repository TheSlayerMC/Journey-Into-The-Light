package net.journey.entity.mob.corba;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityStinky extends JEntityMob {

	public EntityStinky(World world) {
		super(world);
		addMeleeAttackingAI();
		setSize(2.0F, 2.0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		EntityAttributesHelper.setMovementSpeed(this, MobStats.STINKY_SPEED);
		EntityAttributesHelper.setMaxHealth(this, MobStats.STINKY_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.STINKY_DAMAGE);
		this.applyKnowledge(EnumKnowledgeType.CORBA, 1);
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
}
