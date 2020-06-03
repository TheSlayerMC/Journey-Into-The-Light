package net.journey.entity.mob.senterian.mob;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityMiniSentryWalker extends JEntityMob {

	public EntityMiniSentryWalker(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.5F);
		addMeleeAttackingAI();
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
		return JourneyLootTables.MINI_SENTRY_WALKER;
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.MINI_SENTRY_WALKER;
	}
}