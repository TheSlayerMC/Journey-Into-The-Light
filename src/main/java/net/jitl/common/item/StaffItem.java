package net.jitl.common.item;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.init.JSounds;
import net.jitl.util.IEssenceItem;
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

public class StaffItem extends Item implements IEssenceItem {

    protected BiFunction<World, LivingEntity, ThrowableEntity> projectileFactory;

    public StaffItem(Properties properties, BiFunction<World, LivingEntity, ThrowableEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    //TODO add essence cap
    @Override
    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide) {
            JPlayer capability = JPlayer.from(playerIn);
            if (capability != null && capability.essence.consumeEssence(playerIn, 1.0F)) {
                ThrowableEntity throwableEntity = projectileFactory.apply(worldIn, playerIn);
                throwableEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
                worldIn.addFreshEntity(throwableEntity);
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.STAFF_0.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            }
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));

        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
