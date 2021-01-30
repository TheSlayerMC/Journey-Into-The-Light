package net.jitl.common.item;

import net.jitl.common.entity.projectile.EntityThrowableArrow;
import net.jitl.common.helper.EnumItemWeapon;
import net.jitl.init.JSounds;
import net.jitl.init.JTabs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class ThrowableArrowItem extends Item {

    private EnumItemWeapon arrow;

    public ThrowableArrowItem(EnumItemWeapon weapon) {
        super(new Item.Properties().tab(JTabs.RANGED_WEAPONS));
        this.arrow = weapon;
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.STAFF_0.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            EntityThrowableArrow throwableEntity = null;
            try {
                throwableEntity = arrow.getThrowableArrow().getConstructor(World.class, LivingEntity.class).newInstance(worldIn, playerIn);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            throwableEntity.setBaseDamage(arrow.getDamage());
            throwableEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(throwableEntity);
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
