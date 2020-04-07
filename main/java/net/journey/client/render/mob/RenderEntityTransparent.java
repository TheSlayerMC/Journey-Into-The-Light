package net.journey.client.render.mob;

import org.lwjgl.opengl.GL11;

import net.journey.client.render.RenderModMob;
import net.journey.client.render.model.mob.frozen.ModelShatterer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderEntityTransparent extends RenderModMob {

	public RenderEntityTransparent(ModelBase model, float shadow, ResourceLocation tex) {
		super(model, shadow, tex);
	}
	
	public RenderEntityTransparent(ModelBase model, ResourceLocation tex) {
		super(model, tex);
	}
	
}
