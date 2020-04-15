package net.journey.client.render.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSmokeLarge extends ParticleSmokeNormal {

    public ParticleSmokeLarge(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double x, double y, double z) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, x, y, z, 2.0F);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new ParticleSmokeLarge(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}