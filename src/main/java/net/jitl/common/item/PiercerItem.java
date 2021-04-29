package net.jitl.common.item;

import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.util.TriFunction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PiercerItem extends Item {
    protected TriFunction<World, LivingEntity, ItemStack, PiercerEntity> projectileFactory;

    public PiercerItem(Properties properties, TriFunction<World, LivingEntity, ItemStack, PiercerEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide()) {
            PiercerEntity entity = projectileFactory.apply(worldIn, playerIn, stack);
            entity.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
            entity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            if (playerIn.isCreative()) {
                entity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            } else {
                playerIn.inventory.removeItem(stack);
            }
            worldIn.addFreshEntity(entity);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }
        return ActionResult.sidedSuccess(stack, worldIn.isClientSide());
    }
}
