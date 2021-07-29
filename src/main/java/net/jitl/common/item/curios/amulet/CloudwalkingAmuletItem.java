package net.jitl.common.item.curios.amulet;


import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

public class CloudwalkingAmuletItem extends JCurioItem {
    public CloudwalkingAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        if (!player.isOnGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeysCapability.isAmuletPressedEitherSide(player)) {
            JPlayer capability = JPlayer.from(player);
            if (capability != null && capability.essence.checkEssenceEitherSide(player.level.isClientSide(), player, 0.15F)) {
                player.fallDistance = 0.0F;
                player.setDeltaMovement(livingEntity.getDeltaMovement().add(0, 0.1F, 0));
                if (!player.level.isClientSide()) {
                    double halfSize = player.getBbWidth() / 2;
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, halfSize, 0, halfSize, 0);
                }
            }
        }
    }
}
