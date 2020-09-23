package net.journey.entity.mob.senterian.mob;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.entity.util.EntitySentacoin;
import net.journey.entity.util.EntitySentacoinBag;
import net.journey.init.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMiniSentryWalker extends JEntityMob {

	public EntityMiniSentryWalker(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.5F);
		addMeleeAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.MINI_SENTRY_WALKER_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.MINI_SENTRY_WALKER_DAMAGE);
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
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(!world.isRemote) {
			for(int i = 0; i < 2 + rand.nextInt(4); i++) {
				this.world.spawnEntity(new EntitySentacoin(this.world, this.posX, this.posY, this.posZ));
				if(rand.nextInt(10) == 0)
					this.world.spawnEntity(new EntitySentacoinBag(this.world, this.posX, this.posY, this.posZ));
			}
		}
	}
}