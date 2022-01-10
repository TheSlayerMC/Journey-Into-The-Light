package net.jitl.common.item;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.init.JSounds;
import net.jitl.util.IEssenceItem;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class StaffItem extends Item implements IEssenceItem {

    protected BiFunction<Level, LivingEntity, ThrowableProjectile> projectileFactory;

    public StaffItem(Properties properties, BiFunction<Level, LivingEntity, ThrowableProjectile> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide) {
            JPlayer capability = JPlayer.from(playerIn);
            if (capability != null && capability.essence.consumeEssence(playerIn, 1.0F)) {
                ThrowableProjectile throwableEntity = projectileFactory.apply(worldIn, playerIn);
                throwableEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
                worldIn.addFreshEntity(throwableEntity);
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.STAFF_0.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
            }
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
