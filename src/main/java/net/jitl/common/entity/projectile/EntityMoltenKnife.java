package net.jitl.common.entity.projectile;

import net.jitl.common.helper.EnumItemWeapon;
import net.jitl.init.JItems;
import net.jitl.init.JParticleManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityMoltenKnife extends EntityThrowableArrow {

    public EntityMoltenKnife(EntityType<EntityMoltenKnife> type, World world) {
        super(type, world);
    }

    public EntityMoltenKnife(World worldIn, LivingEntity player) {
        super(EnumItemWeapon.MOLTEN_KNIFE.getArrowEntity(), worldIn, player);
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
    public Item pickupItem() {
        return JItems.MOLTEN_KNIFE;
    }
}
