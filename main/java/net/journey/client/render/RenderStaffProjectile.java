package net.journey.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderStaffProjectile extends Render {
	
    public ResourceLocation texture;
    private float scale, red, green, blue;

    public RenderStaffProjectile(ResourceLocation par1, float red, float green, float blue) {
    	super(Minecraft.getMinecraft().getRenderManager());
        texture = par1;
        scale = 1F;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RenderStaffProjectile(ResourceLocation par1, float scaleFactor, float red, float green, float blue) {
    	super(Minecraft.getMinecraft().getRenderManager());
        texture = par1;
        scale = scaleFactor;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void renderProjectile(EntityThrowable projectile, double x, double y, double z) {
        GL11.glPushMatrix();
        this.bindEntityTexture(projectile);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GL11.glColor4f(red, green, blue, 1.0F);
        GlStateManager.enableDepth();
		GlStateManager.disableBlend();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(scale * 0.5F, scale * 0.5F, scale * 0.5F);
        Tessellator t = Tessellator.getInstance();
        WorldRenderer tessellator = t.getWorldRenderer();
        float minU = 0;
        float maxU = 1;
        float minV = 0;
        float maxV = 1;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        float f10 = 0.05625F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.begin(GL11.GL_QUADS, tessellator.getVertexFormat());
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        tessellator.pos(0.0F - f8, 0.0F - f9, 0.0D);
        tessellator.pos(f7 - f8, 0.0F - f9, 0.0D);
        tessellator.pos(f7 - f8, 1.0F - f9, 0.0D);
        tessellator.pos(0.0F - f8, 1.0F - f9, 0.0D);
        t.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderProjectile((EntityThrowable)par1Entity, par2, par4, par6);
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}