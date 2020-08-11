package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.util.EntityBossCrystal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityWraith extends EntityEssenceBoss {

	public EntityWraith(World par1World) {
		super(par1World);
		addMeleeAttackingAI();
	}

	@Override
	protected @Nullable ResourceLocation getLootTable() {
		return null;
	}

	@Override
	protected @Nullable EntityBossCrystal.Type getDeathCrystalType() {
		return null;
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
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.WRAITH_BOSS;
	}
}