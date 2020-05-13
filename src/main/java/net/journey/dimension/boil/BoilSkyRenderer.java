package net.journey.dimension.boil;

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
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class BoilSkyRenderer extends IRenderHandler {

	private static final ResourceLocation SECOND_SUN_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/environment/boil_sun2.png");
	private static final ResourceLocation SUN_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/environment/boil_sun.png");
	private static final ResourceLocation SKY_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/environment/boil_sky.png");
	private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/environment/boil_clouds.png");
	IRenderChunkFactory renderChunkFactory;
	private int starGLCallList;
	private int glSkyList = -1;
	private int glSkyList2 = -1;
	private VertexBuffer starVBO;

	public BoilSkyRenderer() {
		RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		this.generateStars();
		this.renderSky(partialTicks, world, mc);
		this.renderSunAndMoon(partialTicks, world, mc);
		this.renderClouds(partialTicks, mc.player.getPositionEyes(partialTicks).x, mc.player.getPositionEyes(partialTicks).y, mc.player.getPositionEyes(partialTicks).z, mc, world);
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
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderHelper.disableStandardItemLighting();
		float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

		if (afloat != null) {
			GlStateManager.disableTexture2D();
			GlStateManager.shadeModel(7425);
			GlStateManager.pushMatrix();
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F,
					0.0F, 0.0F, 1.0F);
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
				bufferbuilder.pos(f12 * 120.0F, f13 * 120.0F, -f13 * 40.0F * afloat[3])
						.color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
			}

			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.shadeModel(7424);
		}

		GlStateManager.enableTexture2D();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
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
		mc.renderEngine.bindTexture(SECOND_SUN_TEXTURES);

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

	@SideOnly(Side.CLIENT)
	private void renderSky(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
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
		vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT,
				VertexFormatElement.EnumUsage.POSITION, 3));

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

	public void renderClouds(float partialTicks, double x, double y, double z, Minecraft mc, WorldClient world) {
		int pass = EntityRenderer.anaglyphField;
		if (FMLClientHandler.instance().renderClouds(10, partialTicks))
			return;
		if (mc.world.provider.isSurfaceWorld()) {
			if (mc.gameSettings.shouldRenderClouds() == 2) {
				this.renderCloudsFancy(partialTicks, x, y, z, mc, world);
			} else {
				GlStateManager.disableCull();
				int k1 = 32;
				int l1 = 8;
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();
				mc.renderEngine.bindTexture(CLOUDS_TEXTURES);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
						GlStateManager.DestFactor.ZERO);
				Vec3d vec3d = world.getCloudColour(partialTicks);
				float f = (float) vec3d.x;
				float f1 = (float) vec3d.y;
				float f2 = (float) vec3d.z;

				if (pass != 2) {
					float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
					float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
					float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
					f = f3;
					f1 = f4;
					f2 = f5;
				}

				float f9 = 4.8828125E-4F;
				double d5 = (double) ((float) 10 + partialTicks);
				double d3 = x + d5 * 0.029999999329447746D;
				int i2 = MathHelper.floor(d3 / 2048.0D);
				int j2 = MathHelper.floor(z / 2048.0D);
				d3 = d3 - (double) (i2 * 2048);
				double lvt_22_1_ = z - (double) (j2 * 2048);
				float f6 = world.provider.getCloudHeight() - (float) y + 0.33F;
				float f7 = (float) (d3 * 4.8828125E-4D);
				float f8 = (float) (lvt_22_1_ * 4.8828125E-4D);
				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

				for (int k2 = -256; k2 < 256; k2 += 32) {
					for (int l2 = -256; l2 < 256; l2 += 32) {
						bufferbuilder.pos((double) (k2 + 0), (double) f6, (double) (l2 + 32))
								.tex((double) ((float) (k2 + 0) * 4.8828125E-4F + f7),
										(double) ((float) (l2 + 32) * 4.8828125E-4F + f8))
								.color(f, f1, f2, 0.8F).endVertex();
						bufferbuilder.pos((double) (k2 + 32), (double) f6, (double) (l2 + 32))
								.tex((double) ((float) (k2 + 32) * 4.8828125E-4F + f7),
										(double) ((float) (l2 + 32) * 4.8828125E-4F + f8))
								.color(f, f1, f2, 0.8F).endVertex();
						bufferbuilder.pos((double) (k2 + 32), (double) f6, (double) (l2 + 0))
								.tex((double) ((float) (k2 + 32) * 4.8828125E-4F + f7),
										(double) ((float) (l2 + 0) * 4.8828125E-4F + f8))
								.color(f, f1, f2, 0.8F).endVertex();
						bufferbuilder.pos((double) (k2 + 0), (double) f6, (double) (l2 + 0))
								.tex((double) ((float) (k2 + 0) * 4.8828125E-4F + f7),
										(double) ((float) (l2 + 0) * 4.8828125E-4F + f8))
								.color(f, f1, f2, 0.8F).endVertex();
					}
				}

				tessellator.draw();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableBlend();
				GlStateManager.enableCull();
			}
		}
	}

	/**
	 * Checks if the given position is to be rendered with cloud fog
	 */
	public boolean hasCloudFog(double x, double y, double z, float partialTicks) {
		return false;
	}

	private void renderCloudsFancy(float partialTicks, double x, double y, double z, Minecraft mc, WorldClient world) {
		int pass = EntityRenderer.anaglyphField;
		GlStateManager.disableCull();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		float f = 12.0F;
		float f1 = 4.0F;
		double d3 = (double) ((float) 10 + partialTicks);
		double d4 = (x + d3 * 0.029999999329447746D) / 12.0D;
		double d5 = z / 12.0D + 0.33000001311302185D;
		float f2 = world.provider.getCloudHeight() - (float) y + 0.33F;
		int k1 = MathHelper.floor(d4 / 2048.0D);
		int l1 = MathHelper.floor(d5 / 2048.0D);
		d4 = d4 - (double) (k1 * 2048);
		d5 = d5 - (double) (l1 * 2048);
		mc.renderEngine.bindTexture(CLOUDS_TEXTURES);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		Vec3d vec3d = world.getCloudColour(partialTicks);
		float f3 = (float) vec3d.x;
		float f4 = (float) vec3d.y;
		float f5 = (float) vec3d.z;

		if (pass != 2) {
			float f6 = (f3 * 30.0F + f4 * 59.0F + f5 * 11.0F) / 100.0F;
			float f7 = (f3 * 30.0F + f4 * 70.0F) / 100.0F;
			float f8 = (f3 * 30.0F + f5 * 70.0F) / 100.0F;
			f3 = f6;
			f4 = f7;
			f5 = f8;
		}

		float f25 = f3 * 0.9F;
		float f26 = f4 * 0.9F;
		float f27 = f5 * 0.9F;
		float f9 = f3 * 0.7F;
		float f10 = f4 * 0.7F;
		float f11 = f5 * 0.7F;
		float f12 = f3 * 0.8F;
		float f13 = f4 * 0.8F;
		float f14 = f5 * 0.8F;
		float f15 = 0.00390625F;
		float f16 = (float) MathHelper.floor(d4) * 0.00390625F;
		float f17 = (float) MathHelper.floor(d5) * 0.00390625F;
		float f18 = (float) (d4 - (double) MathHelper.floor(d4));
		float f19 = (float) (d5 - (double) MathHelper.floor(d5));
		int i2 = 8;
		int j2 = 4;
		float f20 = 9.765625E-4F;
		GlStateManager.scale(12.0F, 1.0F, 12.0F);

		for (int k2 = 0; k2 < 2; ++k2) {
			if (k2 == 0) {
				GlStateManager.colorMask(false, false, false, false);
			} else {
				switch (pass) {
				case 0:
					GlStateManager.colorMask(false, true, true, true);
					break;
				case 1:
					GlStateManager.colorMask(true, false, false, true);
					break;
				case 2:
					GlStateManager.colorMask(true, true, true, true);
				}
			}

			for (int l2 = -3; l2 <= 4; ++l2) {
				for (int i3 = -3; i3 <= 4; ++i3) {
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
					float f21 = (float) (l2 * 8);
					float f22 = (float) (i3 * 8);
					float f23 = f21 - f18;
					float f24 = f22 - f19;

					if (f2 > -5.0F) {
						bufferbuilder.pos((double) (f23 + 0.0F), (double) (f2 + 0.0F), (double) (f24 + 8.0F))
								.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
										(double) ((f22 + 8.0F) * 0.00390625F + f17))
								.color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
						bufferbuilder.pos((double) (f23 + 8.0F), (double) (f2 + 0.0F), (double) (f24 + 8.0F))
								.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
										(double) ((f22 + 8.0F) * 0.00390625F + f17))
								.color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
						bufferbuilder.pos((double) (f23 + 8.0F), (double) (f2 + 0.0F), (double) (f24 + 0.0F))
								.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
										(double) ((f22 + 0.0F) * 0.00390625F + f17))
								.color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
						bufferbuilder.pos((double) (f23 + 0.0F), (double) (f2 + 0.0F), (double) (f24 + 0.0F))
								.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
										(double) ((f22 + 0.0F) * 0.00390625F + f17))
								.color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
					}

					if (f2 <= 5.0F) {
						bufferbuilder
								.pos((double) (f23 + 0.0F), (double) (f2 + 4.0F - 9.765625E-4F), (double) (f24 + 8.0F))
								.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
										(double) ((f22 + 8.0F) * 0.00390625F + f17))
								.color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
						bufferbuilder
								.pos((double) (f23 + 8.0F), (double) (f2 + 4.0F - 9.765625E-4F), (double) (f24 + 8.0F))
								.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
										(double) ((f22 + 8.0F) * 0.00390625F + f17))
								.color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
						bufferbuilder
								.pos((double) (f23 + 8.0F), (double) (f2 + 4.0F - 9.765625E-4F), (double) (f24 + 0.0F))
								.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
										(double) ((f22 + 0.0F) * 0.00390625F + f17))
								.color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
						bufferbuilder
								.pos((double) (f23 + 0.0F), (double) (f2 + 4.0F - 9.765625E-4F), (double) (f24 + 0.0F))
								.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
										(double) ((f22 + 0.0F) * 0.00390625F + f17))
								.color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
					}

					if (l2 > -1) {
						for (int j3 = 0; j3 < 8; ++j3) {
							bufferbuilder
									.pos((double) (f23 + (float) j3 + 0.0F), (double) (f2 + 0.0F),
											(double) (f24 + 8.0F))
									.tex((double) ((f21 + (float) j3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 8.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) j3 + 0.0F), (double) (f2 + 4.0F),
											(double) (f24 + 8.0F))
									.tex((double) ((f21 + (float) j3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 8.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) j3 + 0.0F), (double) (f2 + 4.0F),
											(double) (f24 + 0.0F))
									.tex((double) ((f21 + (float) j3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 0.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) j3 + 0.0F), (double) (f2 + 0.0F),
											(double) (f24 + 0.0F))
									.tex((double) ((f21 + (float) j3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 0.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (l2 <= 1) {
						for (int k3 = 0; k3 < 8; ++k3) {
							bufferbuilder
									.pos((double) (f23 + (float) k3 + 1.0F - 9.765625E-4F), (double) (f2 + 0.0F),
											(double) (f24 + 8.0F))
									.tex((double) ((f21 + (float) k3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 8.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) k3 + 1.0F - 9.765625E-4F), (double) (f2 + 4.0F),
											(double) (f24 + 8.0F))
									.tex((double) ((f21 + (float) k3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 8.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) k3 + 1.0F - 9.765625E-4F), (double) (f2 + 4.0F),
											(double) (f24 + 0.0F))
									.tex((double) ((f21 + (float) k3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 0.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + (float) k3 + 1.0F - 9.765625E-4F), (double) (f2 + 0.0F),
											(double) (f24 + 0.0F))
									.tex((double) ((f21 + (float) k3 + 0.5F) * 0.00390625F + f16),
											(double) ((f22 + 0.0F) * 0.00390625F + f17))
									.color(f25, f26, f27, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (i3 > -1) {
						for (int l3 = 0; l3 < 8; ++l3) {
							bufferbuilder
									.pos((double) (f23 + 0.0F), (double) (f2 + 4.0F),
											(double) (f24 + (float) l3 + 0.0F))
									.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) l3 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 8.0F), (double) (f2 + 4.0F),
											(double) (f24 + (float) l3 + 0.0F))
									.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) l3 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 8.0F), (double) (f2 + 0.0F),
											(double) (f24 + (float) l3 + 0.0F))
									.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) l3 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 0.0F), (double) (f2 + 0.0F),
											(double) (f24 + (float) l3 + 0.0F))
									.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) l3 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
						}
					}

					if (i3 <= 1) {
						for (int i4 = 0; i4 < 8; ++i4) {
							bufferbuilder
									.pos((double) (f23 + 0.0F), (double) (f2 + 4.0F),
											(double) (f24 + (float) i4 + 1.0F - 9.765625E-4F))
									.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) i4 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 8.0F), (double) (f2 + 4.0F),
											(double) (f24 + (float) i4 + 1.0F - 9.765625E-4F))
									.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) i4 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 8.0F), (double) (f2 + 0.0F),
											(double) (f24 + (float) i4 + 1.0F - 9.765625E-4F))
									.tex((double) ((f21 + 8.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) i4 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
							bufferbuilder
									.pos((double) (f23 + 0.0F), (double) (f2 + 0.0F),
											(double) (f24 + (float) i4 + 1.0F - 9.765625E-4F))
									.tex((double) ((f21 + 0.0F) * 0.00390625F + f16),
											(double) ((f22 + (float) i4 + 0.5F) * 0.00390625F + f17))
									.color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
						}
					}

					tessellator.draw();
				}
			}
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
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