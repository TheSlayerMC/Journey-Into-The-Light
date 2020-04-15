package net.journey.enums;

import net.journey.client.render.particles.*;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleLava;
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
    CONJURING(EntityConjuringFX.class),
    DEPTHS(EntityDepthsPortalFX.class),
    SUMMONING(EntitySummoningFX.class);

    private Class particle;

    EnumParticlesClasses(Class name) {
        particle = name;
    }

    public Class getParticle() {
        return particle;
    }
}