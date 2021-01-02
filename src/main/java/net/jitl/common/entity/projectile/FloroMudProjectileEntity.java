package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntityTypes;
import net.jitl.init.JItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class FloroMudProjectileEntity extends DamagingProjectileEntity implements IRendersAsItem {

    public FloroMudProjectileEntity(EntityType<FloroMudProjectileEntity> type, World world) {
        super(type, world);
    }

    public FloroMudProjectileEntity(World world, LivingEntity thrower, float damage) {
        super(JEntityTypes.FLORO_MUD_PROJECTILE_TYPE, world, thrower, damage);
    }

    @Override
    protected float getGravity() {
        return 0.001F;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(JItems.MUD_BALL);
    }
}