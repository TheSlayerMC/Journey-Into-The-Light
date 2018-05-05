package net.journey.enums;

import net.journey.client.render.particles.EntityFireballFX;

public enum EnumParticlesClasses {

	LAVA(EntityLavaFX.class),
	SMOKE(EntitySmokeFX.class),
	FLAME(EntityFlameFX.class),
	SNOWBALL_POOF(EntitySnowShovelFX.class),
	FIREBALL(EntityFireballFX.class);
	
	private Class particle;
	
	private EnumParticlesClasses(Class name) {
		particle = name;
	}
	
	public Class getParticle() {
		return particle;
	}
}