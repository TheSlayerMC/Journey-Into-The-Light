package net.jitl.common.item.curios.amulate;

import net.jitl.JITL;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity instanceof PlayerEntity) {
            Vector3d vector3d = livingEntity.getDeltaMovement();
            if (vector3d.y < 0.0D && !livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble() && livingEntity.isCrouching()) {
                if (livingEntity.level.getBlockState(livingEntity.blockPosition().below(5)).isCollisionShapeFullBlock(livingEntity.level, livingEntity.blockPosition().below(5))) {
                    livingEntity.setDeltaMovement(vector3d.multiply(1, 0.75, 1));
                    livingEntity.fallDistance = -1.0F;
                    livingEntity.hurtMarked = true;
                    JITL.LOGGER.info(livingEntity.getDeltaMovement().y);
                }
            }
        }
    }
}
