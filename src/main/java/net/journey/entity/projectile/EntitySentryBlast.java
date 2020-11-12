package net.journey.entity.projectile;

import net.journey.client.render.particles.EntityIceballFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntitySentryBlast extends EntityDamagingProjectile {
	
	public EntitySentryBlast(World world) {
		super(world);
	}

	public EntitySentryBlast(World world, EntityLivingBase thrower, float damage) {
		super(world, thrower, damage);
	}
	
	@Override
	public void onUpdate() {
		motionX *= 1.25;
		motionY *= 1.25;
		motionZ *= 1.25;
		super.onUpdate();
		/*EntityIceballFX effect = new EntityIceballFX(this.world, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);*/
	}
	
	@Override
	public float getGravityVelocity() {
		return 0;
	}
} 