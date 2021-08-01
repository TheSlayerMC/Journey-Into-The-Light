package net.jitl.common.item.interactive;

import net.jitl.JITL;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.init.JParticleManager;
import net.jitl.init.JSounds;
import net.jitl.util.IEssenceItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.ChatUtils;

public class MinersPearlItem extends Item implements IEssenceItem {

    public MinersPearlItem(Properties properties) {
        super(properties);
    }

    public @NotNull ActionResult<ItemStack> use(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        boolean flag =
                playerIn.position().y <= worldIn.getSeaLevel() - 2 &&
                        !worldIn.canSeeSky(new BlockPos(playerIn.getX(), playerIn.getY() + (double) playerIn.getEyeHeight(), playerIn.getZ())) &&
                        !playerIn.isInWater() &&
                        !playerIn.isInLava();
        if (!worldIn.isClientSide()) {
            JPlayer capability = JPlayer.from(playerIn);
            assert capability != null;
            Essence essence = capability.essence;

            try {
                if (flag) {
                    if (essence.consumeEssence(playerIn, 10F) && stack.getItem() == this) {
                        playerIn.addEffect(new EffectInstance(Effects.CONFUSION, 140, 2));
                        if (!stack.hasTag()) stack.setTag(new CompoundNBT());
                        CompoundNBT tag = stack.getTag();
                        tag.putBoolean("teleport", true);
                    }
                } else {
                    ChatUtils.format(new TranslationTextComponent("jitl.message.item.miners_pearl"), TextFormatting.DARK_PURPLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            worldIn.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 0.5F);
            worldIn.playSound(playerIn, playerIn.blockPosition(), JSounds.MINERS_PEARL.get(), SoundCategory.PLAYERS, 1.25F, MathHelper.nextFloat(random, 0.95F, 1.55F));
            for (int i = 0; i < 16; ++i) {
                worldIn.addParticle(JParticleManager.MINERS_PEARL.get(),
                        playerIn.getRandomX(0.95D),
                        playerIn.getRandomY() - 0.75D,
                        playerIn.getRandomZ(0.95D),
                        (random.nextDouble() - 0.75D) * 2.0D,
                        random.nextDouble(),
                        (random.nextDouble() - 0.75D) * 2.0D);
            }
        }
        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (stack.hasTag()) {
            CompoundNBT tag = stack.getTag();
            if (tag.getBoolean("teleport")) {
                PlayerEntity player = (PlayerEntity) entity;
                int teleportTimer = tag.getInt("timer");
                System.out.println(teleportTimer);
                if (teleportTimer >= 10) {
                    if (player.level.dimension() == World.OVERWORLD) {
                        player.teleportTo(player.getX(), world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE, player.blockPosition()).getY(), player.getZ());
                    } else {
                        ChatUtils.format(new TranslationTextComponent("jitl.message.item.miners_pearl"), TextFormatting.DARK_PURPLE);
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
