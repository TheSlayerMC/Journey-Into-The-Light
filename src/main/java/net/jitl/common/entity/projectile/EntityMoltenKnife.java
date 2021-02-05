package net.jitl.common.entity.projectile;

import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JParticleManager;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class EntityMoltenKnife extends EntityThrowableArrow implements IRendersAsItem {

    public EntityMoltenKnife(EntityType<EntityMoltenKnife> type, World world) {
        super(type, world);
    }

    public EntityMoltenKnife(World worldIn, LivingEntity owner) {
        super(JEntities.MOLTEN_KNIFE_TYPE, worldIn, owner);
    }

    public EntityMoltenKnife withBaseDamage(double damageIn) {
        super.setBaseDamage(damageIn);
        return this;
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
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return JSounds.MUD_BLOCK_BREAK.get();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult rt) {
        super.onHitEntity(rt);
        if (rt.getEntity() != null) {
            rt.getEntity().setSecondsOnFire(2);
        }
    }

    @Override
    public Item pickupItem() {
        return JItems.MOLTEN_KNIFE;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {//TODO move tosuperclass
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.MOLTEN_KNIFE);
    }
}
