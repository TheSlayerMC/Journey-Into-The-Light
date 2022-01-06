package net.jitl.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class MinersPearlParticle extends TextureSheetParticle {
    private static final Random RANDOM = new Random();
    private final SpriteSet sprites;

    private MinersPearlParticle(ClientLevel clientWorld, double x, double y, double z, double double1_, double motionY, double double_, SpriteSet spriteWithAge) {
        super(clientWorld, x, y, z, 0.5D - RANDOM.nextDouble(), motionY, 0.5D - RANDOM.nextDouble());
        this.sprites = spriteWithAge;
        this.quadSize *= 2.75F;
        this.lifetime = Mth.nextInt(RANDOM, 3, 5);
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteWithAge);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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
            this.yd += 0.000D;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.01F;
            this.yd *= 0.01F;
            this.zd *= 0.01F;
            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new MinersPearlParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }
}
