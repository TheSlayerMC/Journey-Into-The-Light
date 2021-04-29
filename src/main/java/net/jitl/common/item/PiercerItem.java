package net.jitl.common.item;

import net.jitl.common.entity.projectile.EucaPiercerEntity;
import net.jitl.init.JEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PiercerItem extends Item {
    public PiercerItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide()) {
            EucaPiercerEntity entity = new EucaPiercerEntity(JEntities.EUCA_PIERCER_TYPE, playerIn, worldIn, stack, 10);
            entity.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
            entity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(entity);
            playerIn.inventory.removeItem(stack);
        }
        return ActionResult.sidedSuccess(stack, worldIn.isClientSide());
    }
}
