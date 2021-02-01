package net.jitl.common.item;

import net.jitl.common.entity.projectile.base.PiercerEntity;
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

public class ThrowablePiercerItem extends Item {

    private EnumItemWeapon weapon;

    public ThrowablePiercerItem(EnumItemWeapon weapon) {
        super(new Item.Properties().tab(JTabs.RANGED_WEAPONS));
        this.weapon = weapon;
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.STAFF_0.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            PiercerEntity throwableEntity = null;
            try {
                throwableEntity = weapon.getPiercer().getConstructor(World.class, LivingEntity.class, float.class, int.class).newInstance(worldIn, playerIn, (float)weapon.getDamage(), weapon.getMaxBounces());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            throwableEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(throwableEntity);

            if(!playerIn.isCreative()) playerIn.getItemInHand(handIn).shrink(1);
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
