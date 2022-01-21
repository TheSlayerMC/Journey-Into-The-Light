package net.jitl.common.item.interactive;

import net.jitl.core.JITL;
import net.jitl.core.init.JParticleManager;
import net.jitl.core.init.JSounds;
import net.jitl.core.util.IEssenceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.ChatUtils;

public class MinersPearlItem extends Item implements IEssenceItem {

    public MinersPearlItem(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        boolean canTeleport =
                playerIn.position().y <= worldIn.getSeaLevel() - 2 &&
                        !worldIn.canSeeSky(new BlockPos(playerIn.getX(), playerIn.getY() + (double) playerIn.getEyeHeight(), playerIn.getZ())) &&
                        !playerIn.isInWater() &&
                        !playerIn.isInLava() &&
                        playerIn.experienceLevel >= 10;
        if (!worldIn.isClientSide()) {
            try {
                if (canTeleport) {
                    playerIn.giveExperienceLevels(-10);
                    playerIn.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 140, 2));
                    if (!stack.hasTag()) stack.setTag(new CompoundTag());
                    CompoundTag tag = stack.getTag();
                    tag.putBoolean("teleport", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (canTeleport) {
            worldIn.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 0.5F);
            worldIn.playSound(playerIn, playerIn.blockPosition(), JSounds.MINERS_PEARL.get(), SoundSource.PLAYERS, 1.25F, Mth.nextFloat(worldIn.getRandom(), 0.95F, 1.55F));
            for (int i = 0; i < 16; ++i) {
                worldIn.addParticle(JParticleManager.MINERS_PEARL.get(),
                        playerIn.getRandomX(0.95D),
                        playerIn.getRandomY() - 0.75D,
                        playerIn.getRandomZ(0.95D),
                        (worldIn.getRandom().nextDouble() - 0.75D) * 2.0D,
                        worldIn.getRandom().nextDouble(),
                        (worldIn.getRandom().nextDouble() - 0.75D) * 2.0D);
            }
        } else {
            if (worldIn.isClientSide()) {
                playerIn.sendMessage(ChatUtils.format(new TranslatableComponent("jitl.message.item.miners_pearl"), ChatFormatting.DARK_PURPLE), playerIn.getUUID());
            }
            worldIn.playSound(playerIn, playerIn.blockPosition(), JSounds.MINERS_PEARL.get(), SoundSource.PLAYERS, 1.25F, 0.5F);
        }
        return new InteractionResultHolder<>(InteractionResult.PASS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag.getBoolean("teleport")) {
                Player player = (Player) entity;
                int teleportTimer = tag.getInt("timer");
                System.out.println(teleportTimer);
                if (teleportTimer >= 10) {
                    if (player.level.dimension() == Level.OVERWORLD) {
                        player.teleportTo(player.getX(), world.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, player.blockPosition()).getY(), player.getZ());
                    } else {
                        ChatUtils.format(new TranslatableComponent("jitl.message.item.miners_pearl"), ChatFormatting.DARK_PURPLE);
                    }
                    tag.putInt("timer", 0);
                    tag.putBoolean("teleport", false);
                } else {
                    tag.putInt("timer", teleportTimer + 1);
                }
                JITL.LOGGER.info(teleportTimer);
            }
        }
    }
}
