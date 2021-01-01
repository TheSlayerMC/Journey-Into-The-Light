package net.jitl.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class RedFlameParticle extends SpriteTexturedParticle {
	protected RedFlameParticle(ClientWorld worldIn, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(worldIn, x, y, z, motionX, motionY, motionZ);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().move(x, y, z));
		this.setLocationFromBoundingbox();
	}

	@Override
	public float getQuadSize(float scaleFactor) {
		float f = ((float) this.age + scaleFactor) / (float) this.lifetime;
		return this.quadSize * (1.0F - f * f * 0.5F);
	}

	@Override
	public int getLightColor(float partialTick) {
		float f = ((float) this.age + partialTick) / (float) this.lifetime;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		int i = super.getLightColor(partialTick);
		int j = i & 255;
		int k = i >> 16 & 255;
		j = j + (int) (f * 15.0F * 16.0F);
		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite spriteSet) {
			this.sprite = spriteSet;
		}

		public Particle createParticle(@NotNull BasicParticleType typeIn, @NotNull ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			RedFlameParticle redFlameparticle = new RedFlameParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			redFlameparticle.pickSprite(this.sprite);
			return redFlameparticle;
		}
	}
}
