package net.journey.client.render.particles;

import net.journey.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ParticleFloroMud extends Particle {

	private static final ResourceLocation TEXTURE = JITL.rl("particles/floro_mud");
	private final float jParticleScale = 2.0F;
	private final double jPosX;
	private final double jPosY;
	private final double jPosZ;

	public ParticleFloroMud(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		this.jPosX = this.posX = x;
		this.jPosY = this.posY = y;
		this.jPosZ = this.posZ = z;
		this.particleScale = jParticleScale;
		this.particleBlue = 1.0F;
		this.particleGreen = 1.0F;
		this.particleRed = 1.0F;
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURE.toString()));
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entity, float par2, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		this.particleScale = jParticleScale;
		super.renderParticle(buffer, entity, par2, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
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