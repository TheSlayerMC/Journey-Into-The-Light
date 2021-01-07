package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntityTypes;
import net.jitl.init.JItems;
import net.jitl.init.JParticleManager;
import net.jitl.init.JSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class FloroMudProjectileEntity extends DamagingProjectileEntity implements IRendersAsItem {

    public FloroMudProjectileEntity(EntityType<FloroMudProjectileEntity> type, World world) {
        super(type, world);
    }

    public FloroMudProjectileEntity(EntityType<FloroMudProjectileEntity> type, World world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public FloroMudProjectileEntity(World world, LivingEntity thrower, float damage) {
        super(JEntityTypes.FLORO_MUD_PROJECTILE_TYPE, world, thrower, damage);
    }

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
            this.level.addParticle(JParticleManager.MUD.get(),
                    d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y + 0.25F,
                    d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity target) {
        if (target instanceof LivingEntity) {
            EffectInstance effectInstance = new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20);
            ((LivingEntity) target).addEffect(effectInstance);
            target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage());
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!level.isClientSide) {
            if (result.getType() != RayTraceResult.Type.MISS) {
                level.playSound(null, new BlockPos(result.getLocation()), JSounds.MUD_BLOCK_BREAK.get(), SoundCategory.AMBIENT, 1.0F, 1.5F);
            }
        } else {
            remove();
        }
    }

    @Override
    protected float getGravity() {
        return 0.03F;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {//TODO move tosuperclass
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.MUD_BALL);
    }
}