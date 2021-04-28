package net.jitl.common.item.curios.amulet;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        if (!livingEntity.isOnGround() && !livingEntity.isInLava() && !livingEntity.isInWaterOrBubble()) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (!player.isCreative()) {
                JPlayer capability = JPlayer.from(player);
                if (capability != null && capability.essence.get().checkEssenceEitherSide(player.level.isClientSide(), player, 0.75F)) {
                    if (isFloatReady(livingEntity.level, livingEntity.blockPosition().below())) {
                        livingEntity.fallDistance = 0.0F; //TODO: Fix
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
