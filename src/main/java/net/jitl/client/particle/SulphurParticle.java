package net.jitl.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class SulphurParticle extends SpriteTexturedParticle {

    private final IAnimatedSprite sprites;

    protected SulphurParticle(ClientWorld worldIn, double x, double y, double z, double motionX, double motionY, double motionZ, IAnimatedSprite spriteWithAge) {
        super(worldIn, x, y, z, motionX, motionY, motionZ);
        this.sprites = spriteWithAge;
        int i = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
        this.lifetime = (int) Math.max((float) i * 0.9F, 1.0F);
        this.gravity = 0.003F;
        this.setSpriteFromAge(spriteWithAge);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            this.oRoll = this.roll;
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
            }
        }
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

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(@NotNull BasicParticleType typeIn, @NotNull ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SulphurParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }
}
