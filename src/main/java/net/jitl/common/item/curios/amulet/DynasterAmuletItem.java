package net.jitl.common.item.curios.amulet;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.item.Item.Properties;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        Player player = (Player) livingEntity;
        if (!player.isOnGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeysCapability.isAmuletPressedEitherSide(player)) {
            JPlayer capability = JPlayer.from(player);
            if (capability != null && capability.essence.checkEssenceEitherSide(player.level.isClientSide(), player, 0.5F)) {
                boolean bool = isFloatReady(player);
                if (bool) {
                    player.fallDistance = 0;
                    player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0.75F, 1));
                    if (!player.level.isClientSide()) {
                        ((ServerLevel) player.level).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                    }
                }
                if (!player.level.isClientSide()) {
                    ((ServerLevel) player.level).sendParticles(bool ? ParticleTypes.CLOUD : ParticleTypes.SMOKE, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                }
            }
        }
    }

    private boolean isFloatReady(Player player) {
        return player.level.clip(new ClipContext(player.position(),
                player.position().add(0, -5, 0),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.ANY,
                player)).getType() == HitResult.Type.BLOCK;
    }
}
