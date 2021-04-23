package net.jitl.common.item.interactive;

import net.jitl.JITL;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
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
        if (!worldIn.isClientSide()) {
            JPlayer capability = JPlayer.from(playerIn);
            assert capability != null;
            Essence essence = capability.essence.get();
            boolean flag =
                    playerIn.position().y <= worldIn.getSeaLevel() - 2 &&
                            !worldIn.canSeeSky(new BlockPos(playerIn.getX(), playerIn.getY() + (double) playerIn.getEyeHeight(), playerIn.getZ())) &&
                            !playerIn.isInWater() &&
                            !playerIn.isInLava();
            try {
                if (flag) {
                    if (essence.consumeEssence(playerIn, 10F) && stack.getItem() == this) {
                        playerIn.addEffect(new EffectInstance(Effects.CONFUSION, 140, 2));
                        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
                        tag.putBoolean("teleport", true);
                        worldIn.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 0.5F);
                        stack.setTag(tag);
                    }
                } else {
                    ChatUtils.format(new TranslationTextComponent("jitl.message.item.miners_pearl"), TextFormatting.DARK_PURPLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
        if (tag.getBoolean("teleport")) {
            PlayerEntity player = (PlayerEntity) entity;
            int teleportTimer = tag.getInt("timer");
            System.out.println(teleportTimer);
            if (teleportTimer >= 130) {
                player.teleportTo(player.getX(), world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE, player.blockPosition()).getY(), player.getZ());
                tag.putInt("timer", 0);
                tag.putBoolean("teleport", false);
            } else {
                tag.putInt("timer", teleportTimer + 1);
            }
            JITL.LOGGER.info("" + teleportTimer);
            stack.setTag(tag);
        }
    }
}
