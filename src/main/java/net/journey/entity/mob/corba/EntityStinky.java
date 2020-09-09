package net.journey.entity.mob.corba;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityStinky extends JEntityMob {

	public EntityStinky(World world) {
		super(world);
		addMeleeAttackingAI();
		setSize(0.75F, 1.5F);
		setKnowledge(EnumKnowledgeType.CORBA, 2);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		EntityAttributesHelper.setMovementSpeed(this, MobStats.STINKY_SPEED);
		EntityAttributesHelper.setMaxHealth(this, MobStats.STINKY_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.STINKY_DAMAGE);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.STINKY_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.STINKY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.STINKY_DEATH;
	}
}
