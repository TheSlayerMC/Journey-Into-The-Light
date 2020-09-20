package net.journey.entity.base;

import net.journey.api.entity.IEssenceBoss;
import net.journey.init.JourneySounds;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class JEntityMiniBoss extends JEntityMob implements IEssenceBoss {

	public JEntityMiniBoss(World world) {
		super(world);
		isImmuneToFire = true;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public final float getModHealth() {
		return super.getHealth();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.BOSS_DEATH;
	}

	@Override
	public float getSoundVolume() {
		return 10.0F;
	}

	@Override
	public float getModMaxHealth() {
		return getMaxHealth();
	}

	@Nullable
	@Override
	protected abstract ResourceLocation getLootTable();

	@Override
	protected void onDeathUpdate() {
		{
			float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
			float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
			float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;

			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + 0.0D + f, this.posY + 1.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + 0.0D + f, this.posY + 3.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + 0.0D + f, this.posY + 1.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + 0.0D + f, this.posY + 1.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + 0.0D + f, this.posY + 3.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + 0.0D + f, this.posY + 1.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
		}

		int i;
		int j;

		if (!this.world.isRemote) {
			i = 1000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
			}
		}
		this.renderYawOffset = this.rotationYaw += 20.0F;
		i = 2000;

		while (i > 0) {
			j = EntityXPOrb.getXPSplit(i);
			i -= j;
			this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
		}
		this.setDead();

	}
}
