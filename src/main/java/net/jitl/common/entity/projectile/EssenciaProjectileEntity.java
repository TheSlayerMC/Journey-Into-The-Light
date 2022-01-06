package net.jitl.common.entity.projectile;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JParticleManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EssenciaProjectileEntity extends DamagingProjectileEntity {

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, Level world) {
        super(type, world);
    }

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public EssenciaProjectileEntity(Level world, LivingEntity thrower, float damage) {
        super(JEntities.ESSENCIA_PROJECTILE_TYPE, world, thrower, damage);
    }

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
            this.level.addParticle(JParticleManager.RED_FLAME.get(),
                    d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y + 0.25F,
                    d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);
        essenciaBoltEntity.setPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
        essenciaBoltEntity.setARGB(0xff4800);
        level.addFreshEntity(essenciaBoltEntity);
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
