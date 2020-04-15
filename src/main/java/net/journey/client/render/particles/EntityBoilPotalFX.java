package net.journey.client.render.particles;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;

public class EntityBoilPotalFX extends ParticleFlame {

    private double portalPosX, portalPosY, portalPosZ;
	
	public EntityBoilPotalFX(World worldIn, double x, double y, double z, double mx, double my, double mz) {
		super(worldIn, x, y, z, mx, my, mz);
		this.portalPosX = this.posX = x;
        this.portalPosY = this.posY = y;
        this.portalPosZ = this.posZ = z;
	}
	
	@Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float var1 = (float)this.particleAge / (float)this.particleMaxAge;
        float var2 = var1;
        var1 = -var1 + var1 * var1 * 2.0F;
        var1 = 1.0F - var1;
        this.posX = this.portalPosX + this.motionX * var1;
        this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
        this.posZ = this.portalPosZ + this.motionZ * var1;
        if(this.particleAge++ >= this.particleMaxAge) this.setExpired();
    }
}