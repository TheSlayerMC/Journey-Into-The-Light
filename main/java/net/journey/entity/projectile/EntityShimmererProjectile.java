package net.journey.entity.projectile;

import net.journey.client.render.particles.EntityHellstoneFX;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShimmererProjectile extends EntitySmallFireball {

	public EntityShimmererProjectile(World w) {
		super(w);
	}
	
    public EntityShimmererProjectile(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
        extinguish();
    }
	
	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(var1.entityHit != null) var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), 7.0F);
		if(!worldObj.isRemote) this.setDead();
	}

	public void setThrowableHeading(double d, double e, double f, float g, int i) {
		
	}
}