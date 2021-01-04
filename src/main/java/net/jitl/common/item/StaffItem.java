package net.jitl.common.item;

import net.jitl.init.JSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class StaffItem extends Item {

    protected BiFunction<World, LivingEntity, ThrowableEntity> projectileFactory;

    public StaffItem(Properties properties, BiFunction<World, LivingEntity, ThrowableEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    //Copy of ThrowableItem#use method so Essence can be consumed (once it's implemented)
    //TODO add essence cap
    @Override
    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.STAFF_0.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            ThrowableEntity throwableEntity = projectileFactory.apply(worldIn, playerIn);
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
