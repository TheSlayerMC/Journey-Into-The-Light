package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityAttractor extends EntityThrowable {

	public EntityAttractor(World w) {super(w);}
	
	public EntityAttractor(World w, EntityLivingBase e) {
		super(w, e);
		this.motionX *= 6;
		this.motionY *= 6;
		this.motionZ *= 6;
	}

	@Override
	protected void onImpact(RayTraceResult pos) {
		if(pos.entityHit != null && this.getThrower() != null) {
			double 
			x = (this.getThrower().posX - pos.entityHit.posX)/3, 
			y = (this.getThrower().posY-pos.entityHit.posY)/3, 
			z = (this.getThrower().posZ - pos.entityHit.posZ)/3;
			pos.entityHit.addVelocity(x, y, z);
		}
		this.setDead();
	}
	
	@Override
	public void onUpdate() {super.onUpdate();
		this.setDead();
	}
	
	@Override
	public float getGravityVelocity() {return 0;}
	
}