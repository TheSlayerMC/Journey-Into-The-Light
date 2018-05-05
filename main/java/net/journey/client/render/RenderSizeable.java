package net.journey.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderSizeable extends RenderModMob {

	protected float scale;

	public RenderSizeable(ModelBase model, float shadow, float size, ResourceLocation texture) {
		super(model, shadow, texture);
		this.scale = size;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase var1, float var2) {
		GL11.glScalef(this.scale, this.scale, this.scale);
	}
}