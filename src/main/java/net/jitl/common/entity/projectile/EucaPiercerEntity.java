package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.common.entity.projectile.base.PiercerEntity;
import net.jitl.common.helper.EnumItemWeapon;
import net.jitl.init.JItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EucaPiercerEntity extends PiercerEntity {

    public EucaPiercerEntity(EntityType<? extends DamagingProjectileEntity> type, World world, LivingEntity thrower, float damage, int maxBounces) {
        super(type, world, thrower, damage, maxBounces);
    }

    public EucaPiercerEntity(World worldIn, LivingEntity player, float damage, int maxBounces) {
        super(EnumItemWeapon.EUCA_PIERCER.getPiercerEntity(), worldIn, player, damage, maxBounces);
    }

    public EucaPiercerEntity(EntityType<? extends PiercerEntity> type, World world) {
        super(type, world);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(JItems.EUCA_PIERCER);
    }
}
