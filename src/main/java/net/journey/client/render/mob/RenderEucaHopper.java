package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.journey.entity.mob.pet.EntityEucaHopper;
import net.journey.util.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEucaHopper extends RenderModMob {

	private static final ResourceLocation eucaHopper = Textures.eucaHopper;
	private static final ResourceLocation eucaHopperTamed = Textures.eucaHopperTamed;

	public RenderEucaHopper(ModelBase par1ModelBase, ResourceLocation tex) {
		super(par1ModelBase, tex);
	}

	protected ResourceLocation texture(EntityEucaHopper b) {
		return b.isTamed() ? eucaHopperTamed : eucaHopper;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture((EntityEucaHopper)var1);
	}
}