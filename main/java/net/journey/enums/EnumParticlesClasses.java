package net.journey.enums;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.client.particle.ParticleSmokeLarge;
import net.minecraft.client.particle.ParticleSnowShovel;

public enum EnumParticlesClasses {

	LAVA(ParticleLava.class),
	SMOKE(ParticleSmokeLarge.class),
	FLAME(ParticleFlame.class),
	SNOWBALL_POOF(ParticleSnowShovel.class);
	
	private Class particle;
	
	private EnumParticlesClasses(Class name) {
		particle = name;
	}
	
	public Class getParticle() {
		return particle;
	}
}