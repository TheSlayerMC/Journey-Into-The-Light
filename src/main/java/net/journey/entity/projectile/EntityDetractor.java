package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDetractor extends EntityThrowable {

    public EntityDetractor(World world) {
        super(world);
    }

    public EntityDetractor(World world, EntityLivingBase entity) {
        super(world, entity);
        this.motionX *= 6;
        this.motionY *= 6;
        this.motionZ *= 6;
    }

    @Override
    public void onImpact(RayTraceResult position) {
        if (position.entityHit != null && this.getThrower() != null) {
            double
                    x = (position.entityHit.posX - this.getThrower().posX) / 2,
                    y = (position.entityHit.posY - this.getThrower().posY) / 2,
                    z = (position.entityHit.posZ - this.getThrower().posZ) / 2;
            position.entityHit.addVelocity(x, y, z);
        }
        this.setDead();
    }

    @Override
    public float getGravityVelocity() {
        return 0;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.setDead();
    }
}