package net.jitl.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class ThrowableItem extends Item {

    protected BiFunction<EntityType, World, ThrowableEntity> throwableEntityBiFunction;

    public ThrowableItem(Properties properties, BiFunction<EntityType, World, ThrowableEntity> throwableEntityBiFunction) {
        super(properties);
        this.throwableEntityBiFunction = throwableEntityBiFunction;
    }

    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            ThrowableEntity throwableEntity = throwableEntityBiFunction.apply(entityType, world);
            throwableEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(throwableEntity);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
