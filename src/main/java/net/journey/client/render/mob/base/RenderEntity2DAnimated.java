package net.journey.client.render.mob.base;

import net.journey.client.render.Textures;
import net.journey.util.RandHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class RenderEntity2DAnimated extends RenderEntity2D {

	String[] textures;
	int animationSpeed;

	public RenderEntity2DAnimated(RenderManager renderManager, int animationSpeed, String... textures) {
		super(renderManager, null);
		this.textures = textures;
		this.animationSpeed = animationSpeed;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		Random r = new Random();
		for (int i = 0; i < animationSpeed; i++) {
			String name = RandHelper.chooseEqual(r, textures);
			return Textures.getMobTextureLocation(name);
		}
		return null;
	}
}
