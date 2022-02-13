package net.jitl.common.entity.nether;

import net.jitl.common.entity.base.JFlyingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class MiniGhastEntity extends JFlyingEntity {

    public MiniGhastEntity(EntityType<? extends JFlyingEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void addGoals() {

    }

    @Override
    public boolean despawnInPeaceful() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}
