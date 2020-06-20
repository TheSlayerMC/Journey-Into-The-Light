package net.journey.client.render.entity;

import net.journey.entity.functional.EntityBossSpawner;
import net.journey.entity.functional.EntityBossSpawner.State;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Random;

public class RendererBossSpawner extends Render<EntityBossSpawner> {
	public RendererBossSpawner(RenderManager renderManager) {
		super(renderManager);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(@NotNull EntityBossSpawner entity) {
		return null;
	}

	@Override
	public void doRender(@NotNull EntityBossSpawner entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Entity entityToSpawn = entity.getEntityToSpawn();
		BossSpawnerClientHandler clientHandler = entity.getClientHandler();

		if (entityToSpawn != null) {
			GlStateManager.pushMatrix();

			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0, clientHandler.getTranslationY(partialTicks), 0);
			GlStateManager.rotate(clientHandler.getRotationAngle(partialTicks), 0, 1, 0);
			GlStateManager.translate(-x, -y, -z);

			Minecraft.getMinecraft().getRenderManager().renderEntity(entityToSpawn, x, y, z, entityYaw, 0, false);

			State currentState = clientHandler.getCurrentState();
			if (currentState == State.RISING || currentState == State.FALLING) {
				GlStateManager.translate(x, y, z);
				renderStar(clientHandler.getRotationAngle(partialTicks), clientHandler.getTranslationY(partialTicks) / 8, partialTicks);
				GlStateManager.translate(-x, -y, -z);
			}

			GlStateManager.popMatrix();
		}
	}

	private static void renderStar(float rotation, float height, float partialTicks) {
		Tessellator tessellator = Tessellator.getInstance();

		Random rand = new Random(432L);

		/* Shift down a bit */
		GL11.glTranslatef(0f, 0.5f, 0);
		/* Rotate opposite direction at 20% speed */
		GL11.glRotatef((rotation * -0.4f) % 360, 0.5f, 1, 0.5f);

		/* Configuration tweaks */
		float beamStartDistance = 2F;
		float beamEndDistance = 10f;
		float maxOpacity = 192f;

		RenderHelper.disableStandardItemLighting();
		float f2 = 0.0F;

		if (height > 1F) {
			f2 = (height - 0.8F) / 0.2F;
		}

		GlStateManager.disableTexture2D();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.disableAlpha();
		GlStateManager.enableCull();
		GlStateManager.depthMask(false);

		BufferBuilder builder = tessellator.getBuffer();

		final int alpha = (int) (maxOpacity * (1.0F - f2));

		for (int i = 0; i < (height + height * height) / 2.0F * 60.0F; ++i) {
			GlStateManager.rotate(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rand.nextFloat() * 360.0F + height * 90.0F, 0.0F, 0.0F, 1.0F);

			final float f3 = rand.nextFloat() * beamEndDistance + 5.0F + f2 * 10.0F;
			final float f4 = rand.nextFloat() * beamStartDistance + 1.0F + f2 * 2.0F;

			builder.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);

			builder.pos(0.0, 0.0, 0.0).color(255, 255, 255, alpha).endVertex();

			builder.pos(0.0D, 0.0D, 0.0D).color(255, 255, 255, alpha).endVertex();
			builder.pos(-0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			builder.pos(0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			builder.pos(0.0D, f3, (1.0F * f4)).color(255, 0, 255, 0).endVertex();
			builder.pos(-0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			tessellator.draw();
		}

		GlStateManager.depthMask(true);
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();

		RenderHelper.enableStandardItemLighting();
	}
}
