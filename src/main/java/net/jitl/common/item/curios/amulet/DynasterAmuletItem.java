package net.jitl.common.item.curios.amulet;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity.isShiftKeyDown()) {
            if (!livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble()) {
                if (isFloatReady(livingEntity.level, livingEntity.blockPosition().below())) {
                    //TODO: "drain" essence, once essence is added
                    livingEntity.fallDistance = 0.0F;
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(1, 0.75, 1));
                }
            }
        }
    }

    private boolean isFloatReady(World world, BlockPos block) {
        for (int i = 0; i < 5; i++) {
            if (world.getBlockState(block).isCollisionShapeFullBlock(world, block)) {
                return true;
            }
            block = block.below();
        }
        return false;
    }
}
