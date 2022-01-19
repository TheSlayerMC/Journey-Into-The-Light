package net.jitl.common.item.curios.amulet;


import net.jitl.common.capability.player.IJPlayer;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.item.Item.Properties;

public class CloudwalkingAmuletItem extends JCurioItem {
    public CloudwalkingAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
            IJPlayer.get((Player) livingEntity).ifPresent(capability -> {
                Player player = capability.getPlayer();
                if (!player.isOnGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeysCapability.isAmuletPressedEitherSide(player)) {
                if(capability.getEssence().checkEssenceEitherSide(player.level.isClientSide(), player, 0.15F)) {
                    capability.getPlayer().fallDistance = 0.0F;
                    player.setDeltaMovement(livingEntity.getDeltaMovement().add(0, 0.1F, 0));
                    if (!player.level.isClientSide()) {
                        double halfSize = player.getBbWidth() / 2;
                        ((ServerLevel) player.level).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, halfSize, 0, halfSize, 0);
                    }
                }
        }});
    }
}

