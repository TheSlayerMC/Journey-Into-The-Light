package net.journey.client.render.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntityShimmererFX extends Particle {
	
    private double portalPosX, portalPosY, portalPosZ;

    public EntityShimmererFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        super(var1, var2, var4, var6, var8, var10, var12);
        this.motionX = var8;
        this.motionY = var10;
        this.motionZ = var12;
        this.portalPosX = this.posX = var2;
        this.portalPosY = this.posY = var4;
        this.portalPosZ = this.posZ = var6;
        float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleBlue = 0.7F;
        this.particleGreen = 1.0F;
        this.particleRed = 0.0F;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }
    
    @Override
    public void renderParticle(BufferBuilder worldRenderer, Entity e, float f1, float f2, float f3, float f4, float f5, float f6) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/particles/shimmerer.png"));
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        worldRenderer.begin(GL11.GL_QUADS, worldRenderer.getVertexFormat());
        super.renderParticle(worldRenderer, e, f1, f2, f3, f4, f5, f6);
        Tessellator.getInstance().draw();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
    }
    
    @Override
    public int getFXLayer() {
        return 3;
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
        if(this.particleAge++ >= this.particleMaxAge) 
            this.setExpired();
    }
}