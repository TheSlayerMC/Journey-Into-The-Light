package net.jitl.common.item.curios.amulet;

import net.jitl.JITL;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity.isCrouching()) {
            if (!livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble()) {
                if (isFloatReady(livingEntity.level, livingEntity.blockPosition())) {
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(1, 0.75, 1));
                    livingEntity.fallDistance = -1.0F;
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
