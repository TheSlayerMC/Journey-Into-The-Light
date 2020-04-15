package net.journey.client.render.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OreParticleFX extends Particle {
    private float oreParticleScale;

    public OreParticleFX(World w, double x, double y, double z, float r, float g, float b) {
        this(w, x, y, z, 1.0F, r, g, b);
    }

    public OreParticleFX(World w, double x, double y, double z, float scale, float r, float g, float b) {
        super(w, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        float f4 = (float)Math.random() * 0.4F + 0.6F;
        setRBGColorF(r, g, b);
        this.particleScale *= 0.75F;
        this.particleScale *= scale;
        this.oreParticleScale = this.particleScale;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    }
    
    @Override
    public int getFXLayer() {
        return 0;
    }

    @Override
    public void renderParticle(BufferBuilder p_180434_1_, Entity p_180434_2_, float par2, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
    	float var8 = (this.particleAge + par2) / this.particleMaxAge * 3;
        var8 = 1.0F - var8;
        var8 *= var8;
        var8 = 1.0F - var8;
        this.particleScale = this.oreParticleScale * var8;
        super.renderParticle(p_180434_1_, p_180434_2_, par2, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if(this.particleAge++ >= this.particleMaxAge)
            this.setExpired();
        
        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.move(this.motionX, this.motionY, this.motionZ);

        if(this.posY == this.prevPosY) {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;
        if(this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}