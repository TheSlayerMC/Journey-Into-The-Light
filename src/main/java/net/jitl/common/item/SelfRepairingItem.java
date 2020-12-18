package net.jitl.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SelfRepairingItem extends Item {
    public SelfRepairingItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isClientSide() && worldIn.getGameTime() % 20 == 0) {
            if (entityIn instanceof PlayerEntity) {
                if (isDayTime(worldIn) && worldIn.canSeeSky(entityIn.blockPosition())) {
                    stack.hurt(-2, random, null);
                }
            }
        }
    }

    public static boolean isDayTime(World world) {
        final long time = world.getDayTime() % 24000L;
        return time < 13000L || time > 23000L;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!worldIn.isClientSide) {
            itemstack.setDamageValue(40);
        }
        return ActionResult.pass(itemstack);
    }
}