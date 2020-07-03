package net.journey.client.render.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFloroWaterFX extends Particle {

    private final float portalParticleScale;
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    public EntityFloroWaterFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.portalPosX = this.posX = x;
        this.portalPosY = this.posY = y;
        this.portalPosZ = this.posZ = z;
        float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.particleBlue = 0.359595F;
        this.particleGreen = 0.04454F;
        this.particleRed = 0.0F;
        this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
        this.setParticleTextureIndex((int) (Math.random() * 8.0D));
    }

    @Override
    public int getFXLayer() {
        return 0;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float par2, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
        float var8 = (this.particleAge + par2) / this.particleMaxAge * 3;
        var8 = 1.0F - var8;
        var8 *= var8;
        var8 = 1.0F - var8;
        this.particleScale = this.portalParticleScale * var8;
        super.renderParticle(buffer, entity, par2, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float var1 = (float) this.particleAge / (float) this.particleMaxAge;
        float var2 = var1;
        var1 = -var1 + var1 * var1 * 2.0F;
        var1 = 1.0F - var1;
        this.posX = this.portalPosX + this.motionX * var1;
        this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
        this.posZ = this.portalPosZ + this.motionZ * var1;
        if (this.particleAge++ >= this.particleMaxAge)
            this.setExpired();
    }
}