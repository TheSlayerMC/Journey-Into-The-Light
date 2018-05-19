package net.journey.client.render.block;

import java.nio.FloatBuffer;
import java.util.Random;

import net.journey.blocks.tileentity.TileEntitySenterianPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class SenterianPortalRenderer extends TileEntitySpecialRenderer<TileEntitySenterianPortal> {
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation(SlayerAPI.PREFIX + "textures/entity/sentrySky.png");
    private static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(SlayerAPI.PREFIX + "textures/entity/sentryPortal.png");
    private static final Random rand = new Random(31100L);
    FloatBuffer buffer = GLAllocation.createDirectFloatBuffer(16);

	@Override
    public void render(TileEntitySenterianPortal te, double x, double y, double z, float partialTicks, int destroyStage, float f12) {
        float f = (float)this.rendererDispatcher.entityX;
        float f1 = (float)this.rendererDispatcher.entityY;
        float f2 = (float)this.rendererDispatcher.entityZ;
        GlStateManager.disableLighting();
        rand.setSeed(501100L);
        float f3 = 0.75F;

        for (int i = 0; i < 16; ++i) {
            GlStateManager.pushMatrix();
            float f4 = 16 - i;
            float f5 = 0.0625F;
            float f6 = 1.0F / (f4 + 1.0F);

            if (i == 0) {
                this.bindTexture(TEXTURE_1);
                f6 = 0.1F;
                f4 = 65.0F;
                f5 = 0.125F;
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
            }

            if (i >= 1) {
                this.bindTexture(TEXTURE_MAIN);
            }

            if (i == 1) {
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(1, 1);
                f5 = 0.5F;
            }

            float f7 = (float)(-(y + f3));
            float f8 = f7 + (float)ActiveRenderInfo.getPosition().yCoord;
            float f9 = f7 + f4 + (float)ActiveRenderInfo.getPosition().yCoord;
            float f10 = f8 / f9;
            f10 = (float)(y + f3) + f10;
            GlStateManager.translate(f, f10, f2);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.T, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.R, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9473, this.floatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.T, 9473, this.floatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.R, 9473, this.floatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9474, this.floatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.Q);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, Minecraft.getSystemTime() % 700000L / 700000.0F, 0.0F);
            GlStateManager.scale(f5, f5, f5);
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.rotate((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.0F);
            GlStateManager.translate(-f, -f2, -f1);
            f8 = f7 + (float)ActiveRenderInfo.getPosition().yCoord;
            GlStateManager.translate((float)ActiveRenderInfo.getPosition().xCoord * f4 / f8, (float)ActiveRenderInfo.getPosition().zCoord * f4 / f8, -f1);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            float f11 = (rand.nextFloat() * 0.5F + 0.1F) * f6;
            float f12 = (rand.nextFloat() * 0.5F + 0.4F) * f6;
            float f13 = (rand.nextFloat() * 0.5F + 0.5F) * f6;

            if (i == 0) {
                f11 = f12 = f13 = 1.0F * f6;
            }

            worldrenderer.pos(x, y + f3, z).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x, y + f3, z + 1.0D).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1.0D, y + f3, z + 1.0D).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1.0D, y + f3, z).color(f11, f12, f13, 1.0F).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            this.bindTexture(TEXTURE_1);
        }

        GlStateManager.disableBlend();
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.Q);
        GlStateManager.enableLighting();
    }

    public FloatBuffer floatBuffer(float f1, float f2, float f3, float f4) {
        this.buffer.clear();
        this.buffer.put(f1).put(f2).put(f3).put(f4);
        this.buffer.flip();
        return this.buffer;
    }
}