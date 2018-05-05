package net.journey.entity.projectile;

import java.util.Random;

import net.journey.client.render.particles.EntityIceballFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLightningBall extends EntityBasicProjectile {

	public EntityLightningBall(World var1) {
		super(var1);
	}
	
	public EntityLightningBall(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 20; ++i) {
			EntityFX effect = new EntityIceballFX(this.worldObj, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(var1.entityHit != null) { 
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());	
		}
		EntityLightningBolt bolt = new EntityLightningBolt(worldObj, posX, posY, posZ);
		if(!worldObj.isRemote) {
			worldObj.addWeatherEffect(bolt);
			worldObj.createExplosion(this, posX, posY, posZ, 2.0F, true);
			this.setDead();
		}
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}