package net.journey.client.render.block;

import org.lwjgl.opengl.GL11;

import net.journey.entity.mob.boss.EntityBossStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBossStatue extends Render<EntityBossStatue> {

	private ResourceLocation texture;
	private ModelBase model;
	private float size;
	
	public RenderBossStatue(ModelBase model, float size, ResourceLocation texture) {
		super(Minecraft.getMinecraft().getRenderManager());
		this.texture = texture;
		this.model = model;
		this.size = size;
	}

	@Override
	public void doRender(EntityBossStatue entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x, (float)y + 0.75F, (float)z);
		GlStateManager.rotate(180F, 0, 0, 1);
		this.bindEntityTexture(entity);
		GL11.glScalef(this.size, this.size, this.size);
		model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBossStatue entity) {
		return texture;
	}
}