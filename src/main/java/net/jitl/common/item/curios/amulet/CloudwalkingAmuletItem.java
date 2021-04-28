package net.jitl.common.item.curios.amulet;


import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CloudwalkingAmuletItem extends JCurioItem {
    public CloudwalkingAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (!player.isCreative() && !player.isOnGround() && !player.isInLava() && !player.isInWaterOrBubble() && player.isShiftKeyDown()) {
                JPlayer capability = JPlayer.from(player);
                if (capability != null && capability.essence.get().checkEssenceEitherSide(player.level.isClientSide(), player, 0.15F)) {
                    player.fallDistance = 0.0F;
                    player.setDeltaMovement(livingEntity.getDeltaMovement().add(0, 0.1F, 0));
                }
            }
        }
    }
}
