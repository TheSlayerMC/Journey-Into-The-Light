package net.jitl.common.helper;

import net.jitl.common.entity.projectile.EntityMoltenKnife;
import net.jitl.common.entity.projectile.EntityThrowableArrow;
import net.jitl.init.JEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;

public enum EnumItemWeapon {

    MOLTEN_KNIFE(EntityMoltenKnife.class, JEntities.MOLTEN_KNIFE_TYPE, 10);

    private final Class<? extends EntityThrowableArrow> throwableArrow;
    private final EntityType<? extends AbstractArrowEntity> arrow;
    private final double damage;

    EnumItemWeapon(Class<? extends EntityThrowableArrow> throwable, EntityType<? extends AbstractArrowEntity> type, double damage) {
        this.throwableArrow = throwable;
        this.damage = damage;
        this.arrow = type;
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