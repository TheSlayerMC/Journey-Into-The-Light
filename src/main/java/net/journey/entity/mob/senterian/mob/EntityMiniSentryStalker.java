package net.journey.entity.mob.senterian.mob;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityMiniSentryStalker extends JEntityMob {

	public EntityMiniSentryStalker(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.4F);
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
	protected ResourceLocation getLootTable() {
		return JourneyLootTables.MINI_SENTRY_STALKER;
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.MINI_SENTRY_STALKER;
	}
}