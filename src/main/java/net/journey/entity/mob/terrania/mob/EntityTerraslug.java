package net.journey.entity.mob.terrania.mob;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityTerraslug extends JEntityMob {

	public EntityTerraslug(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.4F, 0.4F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.TERRA_SLUG;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.TERRA_SLUG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.TERRA_SLUG_DEATH;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return JourneyLootTables.TERRA_SLUG;
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.TERRASLUG;
	}
}