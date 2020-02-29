package net.journey.enums;

import net.journey.client.render.particles.EntityCloudiaPortalFX;
import net.journey.client.render.particles.EntityConjuringFX;
import net.journey.client.render.particles.EntityDoomFX;
import net.journey.client.render.particles.EntityEarthenFX;
import net.journey.client.render.particles.EntityEnlightmentFX;
import net.journey.client.render.particles.EntityFireballFX;
import net.journey.client.render.particles.EntityFloroWaterFX;
import net.journey.client.render.particles.EntityGreenpaceFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityIceballFX;
import net.journey.client.render.particles.EntityModLavaFX;
import net.journey.client.render.particles.EntityOvergrownFX;
import net.journey.client.render.particles.EntityRockFX;
import net.journey.client.render.particles.EntityWitherFX;
import net.journey.client.render.particles.EntityWizardsFX;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.client.particle.ParticleSmokeLarge;
import net.minecraft.client.particle.ParticleSnowShovel;

public enum EnumParticlesClasses {

	LAVA(ParticleLava.class),
	MOD_LAVA(EntityModLavaFX.class),
	SMOKE(ParticleSmokeLarge.class),
	FLAME(ParticleFlame.class),
	SNOWBALL_POOF(ParticleSnowShovel.class),
	GREENPACE(EntityGreenpaceFX.class),
	HELLSTONE(EntityHellstoneFX.class),
	DOOM(EntityDoomFX.class),
	WIZARDS(EntityWizardsFX.class),
	WITHER(EntityWitherFX.class),
	CLOUDIA(EntityCloudiaPortalFX.class),
	ROCK(EntityRockFX.class),
	OVERGROWN(EntityOvergrownFX.class),
	FLORO_WATER(EntityFloroWaterFX.class),
	ICE_BALL(EntityIceballFX.class),
	FIRE_BALL(EntityFireballFX.class),
	ENLIGHTMENT(EntityEnlightmentFX.class),
	EARTHEN(EntityEarthenFX.class),
	CONJURING(EntityConjuringFX.class);
	
	private Class particle;
	
	private EnumParticlesClasses(Class name) {
		particle = name;
	}
	
	public Class getParticle() {
		return particle;
	}
}