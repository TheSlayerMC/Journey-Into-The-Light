package net.journey.client.render.block;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.journey.blocks.tileentity.TileEntityBossBlock;
import net.journey.blocks.tileentity.TileEntityBossBlock.State;
import net.journey.client.render.Textures;
import net.journey.client.render.model.mob.boss.ModelBeastOfTheNether;
import net.journey.util.handler.Helper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;

public class BossBlockRenderer extends TileEntitySpecialRenderer {

	private static final float PHANTOM_SCALE = 1.5f;

	private static final Random RANDOM = new Random(432L);

	@Override
	public void render(TileEntity entity, double x, double y, double z, float partialTickTime, int destroyProgress, float alpha) {
		TileEntityBossBlock boss = (TileEntityBossBlock)entity;

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180, 1, 0, 0);

		float rotation = boss.getRotation(partialTickTime);
		float progress = boss.getProgress(partialTickTime);
		float offset = boss.getOffset(partialTickTime);

		bindTexture(Textures.netherBeast);

		GL11.glTranslatef(0, -offset, 0);
		GL11.glRotatef(rotation, 0, 1, 0);
		new ModelBeastOfTheNether().render(0.0625F);

		final State state = boss.getState();
		if(state.specialEffects) 
			renderStar(rotation, progress, Tessellator.getInstance(), partialTickTime);

		GL11.glPopMatrix();
	}

	private static void renderStar(float rotation, float progress, Tessellator tes, float partialTicks) {
		/* Shift down a bit */
		GL11.glTranslatef(0f, 0.5f, 0);
		/* Rotate opposite direction at 20% speed */
		GL11.glRotatef(rotation * -0.2f % 360, 0.5f, 1, 0.5f);

		/* Configuration tweaks */
		float BEAM_START_DISTANCE = 2F;
		float BEAM_END_DISTANCE = 10f;
		float MAX_OPACITY = 192f;

		RenderHelper.disableStandardItemLighting();
		float f2 = 0.0F;

		if (progress > 0.8F) {
			f2 = (progress - 0.8F) / 0.2F;
		}

		GlStateManager.disableTexture2D();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.disableAlpha();
		GlStateManager.enableCull();
		GlStateManager.depthMask(false);

		RANDOM.setSeed(432L);

		BufferBuilder wr = tes.getBuffer();

		final int alpha = (int)(MAX_OPACITY * (1.0F - f2));

		for (int i = 0; i < (progress + progress * progress) / 2.0F * 60.0F; ++i) {
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(RANDOM.nextFloat() * 360.0F + progress * 90.0F, 0.0F, 0.0F, 1.0F);

			final float f3 = RANDOM.nextFloat() * BEAM_END_DISTANCE + 5.0F + f2 * 10.0F;
			final float f4 = RANDOM.nextFloat() * BEAM_START_DISTANCE + 1.0F + f2 * 2.0F;

			wr.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);

			wr.pos(0.0, 0.0, 0.0).color(255, 255, 255, alpha).endVertex();

			wr.pos(0.0D, 0.0D, 0.0D).color(255, 255, 255, alpha).endVertex();
			wr.pos(-0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			wr.pos(0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			wr.pos(0.0D, f3, (1.0F * f4)).color(255, 0, 255, 0).endVertex();
			wr.pos(-0.866D * f4, f3, (-0.5F * f4)).color(255, 0, 255, 0).endVertex();
			tes.draw();
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