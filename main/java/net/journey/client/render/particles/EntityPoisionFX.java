package net.journey.client.render.particles;

import net.minecraft.client.particle.ParticleCrit;
import net.minecraft.world.World;

public class EntityPoisionFX extends ParticleCrit {

	public EntityPoisionFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.particleGreen = 1.0F;
	}
}