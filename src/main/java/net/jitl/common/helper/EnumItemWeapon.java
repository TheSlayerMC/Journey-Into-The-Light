package net.jitl.common.helper;

import net.jitl.common.entity.projectile.EntityMoltenKnife;
import net.jitl.common.entity.projectile.EntityThrowableArrow;
import net.jitl.common.entity.projectile.EucaPiercerEntity;
import net.jitl.common.entity.projectile.base.PiercerEntity;
import net.jitl.init.JEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;

public enum EnumItemWeapon {

    MOLTEN_KNIFE(EntityMoltenKnife.class, JEntities.MOLTEN_KNIFE_TYPE, 10),
    EUCA_PIERCER(EucaPiercerEntity.class, JEntities.EUCA_PIERCER_TYPE, 10, 5);

    private Class<? extends EntityThrowableArrow> throwableArrow;
    private EntityType<? extends AbstractArrowEntity> arrow;

    private Class<? extends PiercerEntity> piercerClass;
    private EntityType<? extends PiercerEntity> piercerEntity;


    private double damage;
    private int maxBounces;

    EnumItemWeapon(Class<? extends PiercerEntity> throwable, EntityType<? extends PiercerEntity> type, double damage, int maxBounces) {
        this.piercerClass = throwable;
        this.piercerEntity = type;
        this.damage = damage;
        this.maxBounces = maxBounces;
    }

    EnumItemWeapon(Class<? extends EntityThrowableArrow> throwable, EntityType<? extends AbstractArrowEntity> type, double damage) {
        this.throwableArrow = throwable;
        this.damage = damage;
        this.arrow = type;
    }

    public Class<? extends PiercerEntity> getPiercer() {
        return piercerClass;
    }

    public EntityType<? extends PiercerEntity> getPiercerEntity() {
        return piercerEntity;
    }

    public int getMaxBounces() {
        return maxBounces;
    }

    public EntityType<? extends AbstractArrowEntity> getArrowEntity() {
        return arrow;
    }

    public double getDamage() {
        return damage;
    }

    public Class<? extends EntityThrowableArrow> getThrowableArrow() {
        return throwableArrow;
    }

}