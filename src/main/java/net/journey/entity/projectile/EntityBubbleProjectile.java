package net.journey.entity.projectile;

import net.journey.JITL;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class EntityBubbleProjectile extends EntityLargeFireball {

    public EntityBubbleProjectile(World w) {
        super(w);
    }

    public EntityBubbleProjectile(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
        this.setFire(0);
    }

    @Override
    protected void onImpact(RayTraceResult var1) {
        if (var1.entityHit != null)
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), 200.0F);
        //((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
        //((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(Potion.harm.id, 100, 1));
        if (!world.isRemote) this.setDead();
    }

    public void setThrowableHeading(double d, double e, double f, float g, int i) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        Random rand = new Random();
        super.onUpdate();
        for (int i = 0; i < 20; ++i) {
            JITL.proxy.spawnParticle(EnumParticlesClasses.ICE_BALL, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
        }
    }
}