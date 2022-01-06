package net.jitl.common.item.throwable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item.Properties;

public class ThrowableItem extends Item {

    protected BiFunction<Level, LivingEntity, Projectile> projectileFactory;
    @Nullable
    private Supplier<SoundEvent> sound;

    public ThrowableItem(Properties properties, BiFunction<Level, LivingEntity, Projectile> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    public ThrowableItem setSound(Supplier<SoundEvent> sound) {
        this.sound = sound;
        return this;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (sound != null) {
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), sound.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        }
        if (!worldIn.isClientSide) {
            Projectile projectile = projectileFactory.apply(worldIn, playerIn);
            projectile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(projectile);

            if (!playerIn.isCreative()) {
                itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
