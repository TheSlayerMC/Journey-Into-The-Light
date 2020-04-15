package net.journey.dimension.cloudia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class CloudiaSkyRenderer extends IRenderHandler {

    private static final ResourceLocation MOON_TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/environment/cloudia_moon.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/environment/cloudia_sun.png");
    private static final ResourceLocation SKY_TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/environment/cloudia_sky.png");
    private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/environment/cloudia_clouds.png");
    IRenderChunkFactory renderChunkFactory;
    private int starGLCallList;
    private int glSkyList = -1;
    private int glSkyList2 = -1;
    private VertexBuffer starVBO;

    public CloudiaSkyRenderer() {
        RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        this.generateStars();
        //this.renderSky(partialTicks, world, mc);
        this.renderSunAndMoon(partialTicks, world, mc);
    }

    @SideOnly(Side.CLIENT)
    public void renderSunAndMoon(float partialTicks, WorldClient world, Minecraft mc) {
        RenderGlobal rg = mc.renderGlobal;
        int pass = EntityRenderer.anaglyphField;

        GlStateManager.disableTexture2D();
        Vec3d vec3d = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float f = (float) vec3d.x;
        float f1 = (float) vec3d.y;
        float f2 = (float) vec3d.z;

        float anaglyphR = 0.0F;
        float anaglyphG = 0.0F;
        float anaglyphB = 0.0F;

        if (pass != 2) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        GlStateManager.color(f, f1, f2);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(f, f1, f2);
        GlStateManager.callList(this.glSkyList);
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

        if (afloat != null) {
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];

            if (mc.gameSettings.anaglyph) {
                float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
                float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
                float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
                f6 = f9;
                f7 = f10;
                f8 = f11;
            }

            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
            int l1 = 16;

            for (int j2 = 0; j2 <= 16; ++j2) {
                float f21 = (float) j2 * ((float) Math.PI * 2F) / 16.0F;
                float f12 = MathHelper.sin(f21);
                float f13 = MathHelper.cos(f21);
                bufferbuilder.pos(f12 * 120.0F, f13 * 120.0F, -f13 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
        }

        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float f16 = 1.0F - world.getRainStrength(partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);

        /* sun rendering starts here */
        float f17 = 30.0F;
        mc.renderEngine.bindTexture(SUN_TEXTURES);

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-f17, 100.0D, -f17).tex(0.0D, 0.0D).endVertex();
        bufferbuilder.pos(f17, 100.0D, -f17).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos(f17, 100.0D, f17).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos(-f17, 100.0D, f17).tex(0.0D, 1.0D).endVertex();
        /* sun rendering ends here */

        tessellator.draw();
        f17 = 20.0F;
        mc.renderEngine.bindTexture(MOON_TEXTURES);


        int k1 = world.getMoonPhase();
        int i2 = k1 % 4;
        int k2 = k1 / 4 % 2;
        float f22 = (float) (i2 + 0) / 4.0F;
        float f23 = (float) (k2 + 0) / 2.0F;
        float f24 = (float) (i2 + 1) / 4.0F;
        float f14 = (float) (k2 + 1) / 2.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-f17, -100.0D, f17).tex(f24, f14).endVertex();
        bufferbuilder.pos(f17, -100.0D, f17).tex(f22, f14).endVertex();
        bufferbuilder.pos(f17, -100.0D, -f17).tex(f22, f23).endVertex();
        bufferbuilder.pos(-f17, -100.0D, -f17).tex(f24, f23).endVertex();
        tessellator.draw();
        GlStateManager.disableTexture2D();
        float f15 = world.getStarBrightness(partialTicks) * f16;

        if (f15 > 0.0F) {
            GlStateManager.color(f15, f15, f15, f15);
            GlStateManager.callList(this.starGLCallList);
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.color(0F, 0F, 0F);
        double d3 = mc.player.getPositionEyes(partialTicks).y - world.getHorizon();

        if (d3 < 0.0D) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            GlStateManager.callList(this.glSkyList2);
            GlStateManager.popMatrix();
            float f18 = 1.0F;
            float f19 = -((float) (d3 + 65.0D));
            float f20 = -1.0F;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(-1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }
        if (world.provider.isSkyColored()) {
            GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
        } else {
            GlStateManager.color(f, f1, f2);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -((float) (d3 - 16.0D)), 0.0F);
        GlStateManager.callList(this.glSkyList2);
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
    }

    private void renderSky(float partialTicks, WorldClient world, Minecraft mc) {
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.depthMask(false);
        mc.renderEngine.bindTexture(SKY_TEXTURES);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        for (int k1 = 0; k1 < 6; ++k1) {
            GlStateManager.pushMatrix();

            if (k1 == 1) {
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 2) {
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 3) {
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 4) {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (k1 == 5) {
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos(-100.0D, -100.0D, -100.0D).tex(0.0D, 0.0D).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(-100.0D, -100.0D, 100.0D).tex(0.0D, 16.0D).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(100.0D, -100.0D, 100.0D).tex(16.0D, 16.0D).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(100.0D, -100.0D, -100.0D).tex(16.0D, 0.0D).color(40, 40, 40, 255).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
        }

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
    }

    private void generateStars() {
        VertexFormat vertexBufferFormat = new VertexFormat();
        vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();

        if (this.starVBO != null) {
            this.starVBO.deleteGlBuffers();
        }

        if (this.starGLCallList >= 0) {
            GLAllocation.deleteDisplayLists(this.starGLCallList);
            this.starGLCallList = -1;
        }

        if (OpenGlHelper.useVbo()) {
            this.starVBO = new net.minecraft.client.renderer.vertex.VertexBuffer(vertexBufferFormat);
            this.renderStars(vertexbuffer);
            vertexbuffer.finishDrawing();
            vertexbuffer.reset();
            this.starVBO.bufferData(vertexbuffer.getByteBuffer());
        } else {
            this.starGLCallList = GLAllocation.generateDisplayLists(1);
            GlStateManager.pushMatrix();
            GlStateManager.glNewList(this.starGLCallList, 4864);
            this.renderStars(vertexbuffer);
            tessellator.draw();
            GlStateManager.glEndList();
            GlStateManager.popMatrix();
        }
    }

    private void renderStars(BufferBuilder worldRendererIn) {
        Random random = new Random(10842L);
        worldRendererIn.begin(7, DefaultVertexFormats.POSITION);

        for (int i = 0; i < 3000; ++i) // TF - 1500 -> 3000
        {
            double d0 = random.nextFloat() * 2.0F - 1.0F;
            double d1 = random.nextFloat() * 2.0F - 1.0F;
            double d2 = random.nextFloat() * 2.0F - 1.0F;
            double d3 = 0.15F + random.nextFloat() * 0.1F;
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d4 < 1.0D && d4 > 0.01D) {
                d4 = 1.0D / Math.sqrt(d4);
                d0 = d0 * d4;
                d1 = d1 * d4;
                d2 = d2 * d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for (int j = 0; j < 4; ++j) {
                    double d17 = 0.0D;
                    double d18 = (double) ((j & 2) - 1) * d3;
                    double d19 = (double) ((j + 1 & 2) - 1) * d3;
                    double d20 = 0.0D;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    worldRendererIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
    }
}