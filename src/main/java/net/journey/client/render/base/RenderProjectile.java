package net.journey.client.render.base;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

//TODO T should extend EntityBasicProjectile?
public class RenderProjectile<T extends Entity> extends Render<T> {

    public ResourceLocation texture;
    private float scale;

    public RenderProjectile(RenderManager renderManager, ResourceLocation texture) {
        this(renderManager, texture, 1F);
    }

    public RenderProjectile(RenderManager renderManager, ResourceLocation texture, float scaleFactor) {
        super(renderManager);
        this.texture = texture;
        scale = scaleFactor;
    }

    public void renderProjectile(T projectile, double x, double y, double z) {
        GlStateManager.pushMatrix();

        this.bindEntityTexture(projectile);
        GlStateManager.translate(x, y + projectile.height / 2, z);
        GlStateManager.enableRescaleNormal();

        GlStateManager.scale(scale * 0.5F, scale * 0.5F, scale * 0.5F);

        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(projectile));
        }

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
        buffer.pos(-0.5D, -0.5D, 0.0D).tex(0, 1).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(0.5D, -0.5D, 0.0D).tex(1, 1).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(0.5D, 0.5D, 0.0D).tex(1, 0).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(-0.5D, 0.5D, 0.0D).tex(0, 0).normal(0.0F, 1.0F, 0.0F).endVertex();
        tessellator.draw();

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();

        GlStateManager.popMatrix();
    }

    @Override
    public void doRender(@NotNull T projectile, double par2, double par4, double par6, float par8, float par9) {
        this.renderProjectile(projectile, par2, par4, par6);
    }

    @Override
    protected ResourceLocation getEntityTexture(@NotNull T projectile) {
        return texture;
    }
}