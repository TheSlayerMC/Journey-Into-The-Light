package net.jitl.common.item.curios.amulet;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.pressedkeys.PressedKeysCapability;
import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.server.ServerWorld;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        if (!player.isOnGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeysCapability.isAmuletPressedEitherSide(player)) {
            JPlayer capability = JPlayer.from(player);
            if (capability != null && capability.essence.checkEssenceEitherSide(player.level.isClientSide(), player, 0.5F)) {
                boolean bool = isFloatReady(player);
                if (bool) {
                    player.fallDistance = 0;
                    player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0.75F, 1));
                    if (!player.level.isClientSide()) {
                        ((ServerWorld) player.level).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                    }
                }
                if (!player.level.isClientSide()) {
                    ((ServerWorld) player.level).sendParticles(bool ? ParticleTypes.CLOUD : ParticleTypes.SMOKE, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                }
            }
        }
    }

    private boolean isFloatReady(PlayerEntity player) {
        return player.level.clip(new RayTraceContext(player.position(),
                player.position().add(0, -5, 0),
                RayTraceContext.BlockMode.COLLIDER,
                RayTraceContext.FluidMode.ANY,
                player)).getType() == RayTraceResult.Type.BLOCK;
    }
}
