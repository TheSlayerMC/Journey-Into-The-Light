package net.journey.entity.projectile.launcher;

import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityForestPlasma extends EntityThrowable {

    public EntityForestPlasma(World var1) {
        super(var1);
    }

    public EntityForestPlasma(World var1, EntityLivingBase base) {
        super(var1, base);
    }

    @Override
    protected void onImpact(RayTraceResult var1) {
        if (var1.entityHit != null && var1.entityHit instanceof EntityLivingBase) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 10);
            ((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.poison), 100, 2));
        }
        if (!world.isRemote) this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }
}