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
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class EntityWitherFX extends Particle {

	private static final ResourceLocation TEXTURE = JITL.rl("particles/wither");
	private final float jParticleScale;
	private final double jPosX;
	private final double jPosY;
	private final double jPosZ;

	public EntityWitherFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		this.jPosX = this.posX = x;
		this.jPosY = this.posY = y;
		this.jPosZ = this.posZ = z;
		this.jParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		this.particleBlue = 1.0F;
		this.particleGreen = 1.0F;
		this.particleRed = 1.0F;
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURE.toString()));
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void renderParticle(BufferBuilder builder, Entity entity, float par2, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		float var8 = (this.particleAge + par2) / this.particleMaxAge * 3;
		var8 = 1.0F - var8;
		var8 *= var8;
		var8 = 1.0F - var8;
		this.particleScale = (this.jParticleScale * var8 * 4);
		super.renderParticle(builder, entity, par2, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_,
				p_180434_8_);
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		float var1 = (float) this.particleAge / (float) this.particleMaxAge;
		float var2 = var1;
		var1 = -var1 + var1 * var1 * 2.0F;
		var1 = 1.0F - var1;
		this.posX = this.jPosX + this.motionX * var1;
		this.posY = this.jPosY + this.motionY * var1 + (1.0F - var2);
		this.posZ = this.jPosZ + this.motionZ * var1;
		if (this.particleAge++ >= this.particleMaxAge)
			this.setExpired();
	}

	@SubscribeEvent
	public static void onPreTextureStich(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(TEXTURE);
	}
}