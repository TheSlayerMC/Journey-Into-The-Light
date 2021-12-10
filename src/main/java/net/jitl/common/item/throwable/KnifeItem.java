package net.jitl.common.item.throwable;

import net.jitl.common.entity.projectile.KnifeEntity;
import net.jitl.util.TriFunction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class KnifeItem extends Item {
    protected TriFunction<LivingEntity, World, ItemStack, KnifeEntity> projectileFactory;

    public KnifeItem(Properties properties, TriFunction<LivingEntity, World, ItemStack, KnifeEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide()) {
            KnifeEntity entity = projectileFactory.apply(playerIn, worldIn, stack);

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

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}
