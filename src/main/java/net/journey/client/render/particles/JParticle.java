package net.journey.client.render.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class JParticle extends Particle {


	public JParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

	public JParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}

	@Override
	public void onUpdate() {
		float age = ((float) particleAge / (float) particleMaxAge);
		if (this.particleAge++ >= this.particleMaxAge)
			this.setExpired();
	}
}
