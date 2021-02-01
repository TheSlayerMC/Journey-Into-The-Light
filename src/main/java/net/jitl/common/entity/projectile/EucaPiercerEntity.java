package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.PiercerEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EucaPiercerEntity extends PiercerEntity {
    public EucaPiercerEntity(World worldIn, LivingEntity thrower, float damage, int maxBounces) {
        super(JEntities.EUCA_PIERCER_TYPE, worldIn, thrower, damage, maxBounces);
    }

    public EucaPiercerEntity(EntityType<? extends PiercerEntity> type, World world) {
        super(type, world);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(JItems.EUCA_PIERCER);
    }
}
