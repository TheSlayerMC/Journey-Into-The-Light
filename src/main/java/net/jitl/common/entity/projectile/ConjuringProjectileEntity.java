package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JParticleManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
//TODO maybe merge with FloroMud?
public class ConjuringProjectileEntity extends DamagingProjectileEntity {

    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, Level world) {
        super(type, world);
    }

    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public ConjuringProjectileEntity(Level world, LivingEntity thrower, float damage) {
        super(JEntities.CONJURING_PROJECTILE_TYPE, world, thrower, damage);
    }

    //TODO do something with duplicated stuff
    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientTick() {
        super.onClientTick();
        int count = 2;
        Vec3 vector3d = this.getDeltaMovement();
        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        for (int i = 0; i < count; ++i) {
            this.level.addParticle(JParticleManager.CONJURING.get(),
                    d0 - vector3d.x * 0.15D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y + 0.05F,
                    d2 - vector3d.z * 0.15D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        if (target instanceof LivingEntity && target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage())) {
            MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.POISON, 60);
            ((LivingEntity) target).addEffect(effectInstance);
        }
    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}