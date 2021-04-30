package net.jitl.common.item.throwable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ThrowableItem extends Item {

    protected BiFunction<World, LivingEntity, ProjectileEntity> projectileFactory;
    @Nullable
    private Supplier<SoundEvent> sound;

    public ThrowableItem(Properties properties, BiFunction<World, LivingEntity, ProjectileEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    public ThrowableItem setSound(Supplier<SoundEvent> sound) {
        this.sound = sound;
        return this;
    }

    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (sound != null) {
            worldIn.playSound(playerIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), sound.get(), SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        }
        if (!worldIn.isClientSide) {
            ProjectileEntity projectile = projectileFactory.apply(worldIn, playerIn);
            projectile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(projectile);

            if (!playerIn.isCreative()) {
                itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }

        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
