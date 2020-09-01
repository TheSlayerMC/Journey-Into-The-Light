package net.journey.entity.mob.senterian.mob;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMiniSentryLord extends JEntityMob {

	public EntityMiniSentryLord(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.2F);
		addMeleeAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, 20);
		EntityAttributesHelper.setAttackDamage(this, 5);
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
		return JourneyLootTables.MINI_SENTRY_LORD;
	}
}