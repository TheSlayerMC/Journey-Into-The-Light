package net.journey.client.render.mob;

import net.journey.client.render.model.mob.frozen.ModelShatterer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderShatterer extends RenderLiving {
	
	int time;

	public RenderShatterer() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelShatterer(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("essence:textures/models/mobs/shatterer.png");
	}
	
	@Override
	public void preRenderCallback(EntityLivingBase e, float f) {
		time++;
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.translate(0, -1.5, 0);
		//GlStateManager.rotate(System.currentTimeMillis(), 0, 0, 1);
		GlStateManager.rotate(time, 0, 0, 1);
		GlStateManager.translate(0, 1.5, 0);
	}
}