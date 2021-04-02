package net.jitl.common.item.curios.amulate;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (!livingEntity.level.isClientSide()) {
            if (livingEntity.getMotionDirection().getAxisDirection() == Direction.AxisDirection.NEGATIVE
                    && !livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble() && livingEntity.isCrouching()) {
                if (livingEntity.level.getBlockState(livingEntity.blockPosition().below(2)).isCollisionShapeFullBlock(livingEntity.level, livingEntity.blockPosition().below(5))) {
                    livingEntity.lerpMotion(0, 0.55, 0);
                    livingEntity.fallDistance = -1.0F;
                }
            }
        }
    }
}
