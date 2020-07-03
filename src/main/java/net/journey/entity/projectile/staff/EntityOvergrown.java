package net.journey.entity.projectile.staff;

import net.journey.JITL;
import net.journey.entity.projectile.EntityDamagingProjectile;
import net.journey.enums.EnumParticlesClasses;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EntityOvergrown extends EntityDamagingProjectile {

	public EntityOvergrown(World var1) {
		super(var1);
	}

	public EntityOvergrown(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        Random rand = new Random();
        super.onUpdate();
        for (int i = 0; i < 20; ++i) {
            JITL.proxy.spawnParticle(EnumParticlesClasses.OVERGROWN, this.world, this.posX, this.posY - 1.0F, this.posZ, false);

        }
    }

	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (rayTraceResult.entityHit != null) {
			rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase) rayTraceResult.entityHit).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.poison), 100, 1));
		}
		if (!world.isRemote) this.setDead();
	}

    @Override
    protected float getGravityVelocity() {
        return 0.032F;
    }
}