package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CalciaBurstEntity extends DamagingProjectileEntity {
    public CalciaBurstEntity(EntityType<? extends CalciaBurstEntity> type, World world) {
        super(type, world);
    }

    public CalciaBurstEntity(EntityType<? extends CalciaBurstEntity> type, World world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public CalciaBurstEntity(World world, LivingEntity thrower, float damage) {
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
    protected void onEntityImpact(RayTraceResult result, Entity target) {
        target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage());
    }

    @Override
    protected boolean shouldDespawn() {
        return tickCount >= 20 * 50;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {//TODO move tosuperclass
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
