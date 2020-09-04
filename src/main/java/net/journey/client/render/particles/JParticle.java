package net.journey.client.render.particles;

import net.journey.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JParticle extends Particle {

	private static ResourceLocation TEXTURE = JITL.rl("particles/floro_mud");

	public JParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

	public JParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}

	public JParticle setTexture(ResourceLocation texture) {
		TEXTURE = texture;
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString()));
		return this;
	}

	@Override
	public void onUpdate() {
		if (this.particleAge++ >= this.particleMaxAge)
			this.setExpired();
	}

	@SubscribeEvent
	public static void onPreTextureStich(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(TEXTURE);
	}
}
