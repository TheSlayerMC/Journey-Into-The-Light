package net.jitl.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class MudParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    protected MudParticle(ClientLevel worldIn, double x, double y, double z, SpriteSet spriteWithAge) {
        super(worldIn, x, y, z, 0.0F, -0.15F, 0.0F);
        this.sprites = spriteWithAge;
        int i = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
        this.lifetime = (int) Math.max((float) i * 0.9F, 1.0F);
        this.gravity = 0.06F;
        this.setSpriteFromAge(spriteWithAge);
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
            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }
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
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new MudParticle(worldIn, x, y, z, this.sprites);
        }
    }
}
