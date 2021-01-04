package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntityTypes;
import net.jitl.init.JParticleManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
//TODO maybe merge with FloroMud?
@OnlyIn(value = Dist.CLIENT)
public class ConjuringProjectileEntity extends DamagingProjectileEntity {

    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, World world) {
        super(type, world);
        this.setPotionEffect(new EffectInstance(Effects.POISON));
    }

    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, World world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
        this.setPotionEffect(new EffectInstance(Effects.POISON));
    }

    public ConjuringProjectileEntity(World world, LivingEntity thrower, float damage) {
        super(JEntityTypes.CONJURING_PROJECTILE_TYPE, world, thrower, damage);
    }

    //TODO do something with duplicated stuff
    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientTick() {
        super.onClientTick();
        int count = 2;
        Vector3d vector3d = this.getDeltaMovement();
        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        for (int i = 0; i < count; ++i) {
            this.level.addParticle(JParticleManager.CONJURING.get(),
                    d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y,
                    d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}