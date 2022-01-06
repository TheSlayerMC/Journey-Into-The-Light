package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

public class CalciaBurstEntity extends DamagingProjectileEntity {
    public CalciaBurstEntity(EntityType<? extends CalciaBurstEntity> type, Level world) {
        super(type, world);
    }

    public CalciaBurstEntity(EntityType<? extends CalciaBurstEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public CalciaBurstEntity(Level world, LivingEntity thrower, float damage) {
        this(JEntities.CALCIA_BURST_TYPE, world, thrower, damage);
    }

    @Override
    public void onClientTick() {
        super.onClientTick();
        this.level.addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, getX(), getY() + (this.getBbHeight() / 2), getZ(), 1, 0,1);
    }

    @Override
    protected float getGravity() {
        return 0.004F;
    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage());
    }

    @Override
    protected boolean shouldDespawn() {
        return tickCount >= 20 * 50;
    }

    @Override
    public Packet<?> getAddEntityPacket() {//TODO move tosuperclass
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
