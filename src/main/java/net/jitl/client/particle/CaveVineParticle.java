package net.jitl.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CaveVineParticle extends TextureSheetParticle {
    private final float rotSpeed;
    private final SpriteSet sprites;

    //Almost identical to FallingDustParticle but with fullBright, and doesn't stop when it lands on the ground
    private CaveVineParticle(ClientLevel worldIn, double x, double y, double z, SpriteSet spriteWithAge) {
        super(worldIn, x, y, z);
        this.sprites = spriteWithAge;
        this.quadSize *= 0.67499995F;
        int i = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
        this.lifetime = (int) Math.max((float) i * 0.9F, 1.0F);
        this.setSpriteFromAge(spriteWithAge);
        this.rotSpeed = ((float) Math.random() - 0.5F) * 0.1F;
        this.roll = (float) Math.random() * ((float) Math.PI * 2F);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(((float) this.age + scaleFactor) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public int getLightColor(float partialTick) {
        float f = ((float) this.age + partialTick) / (float) this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int) (f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
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
            this.roll += (float) Math.PI * this.rotSpeed * 2.0F;
            this.move(this.xd, this.yd, this.zd);
            this.yd -= 0.003F;
            this.yd = Math.max(this.yd, -0.14F);
            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new CaveVineParticle(worldIn, x, y, z, this.sprites);
        }
    }
}
