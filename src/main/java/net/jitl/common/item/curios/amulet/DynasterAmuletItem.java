package net.jitl.common.item.curios.amulet;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties, String ability, String negativeEffects) {
        super(properties, ability, negativeEffects);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity.isShiftKeyDown()) {
            if (!livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble()) {
                if (isFloatReady(livingEntity.level, livingEntity.blockPosition().below())) {
                    JPlayer capability = JPlayer.from((PlayerEntity) livingEntity);
                    if (capability != null && capability.essence.get().checkEssenceEitherSide(livingEntity.level.isClientSide(), 0.75F)) {
                        livingEntity.fallDistance = 0.0F;
                        livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(1, 0.75F, 1));
                    }
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
