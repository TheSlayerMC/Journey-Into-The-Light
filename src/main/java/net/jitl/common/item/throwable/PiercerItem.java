package net.jitl.common.item.throwable;

import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.init.JEnchantments;
import net.jitl.util.TriFunction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PiercerItem extends Item {
    protected TriFunction<World, LivingEntity, ItemStack, PiercerEntity> projectileFactory;

    public PiercerItem(Properties properties, TriFunction<World, LivingEntity, ItemStack, PiercerEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide()) {
            PiercerEntity entity = projectileFactory.apply(worldIn, playerIn, stack);
            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
            if (j > 0) {
                entity.setBaseDamage(entity.getBaseDamage() + (double) j * 0.5D + 0.5D);
            }

            int i = EnchantmentHelper.getItemEnchantmentLevel(JEnchantments.LIGHTWEIGHT.get(), stack);
            if (i > 0) {
                entity.setVelocityMultiplier(i);
            }

            double k = EnchantmentHelper.getItemEnchantmentLevel(JEnchantments.AMBIT.get(), stack);
            if (k > 0) {
                entity.setRangeAddend(k * 2);
            }

            int f = EnchantmentHelper.getItemEnchantmentLevel(JEnchantments.SCORCHING.get(), stack);
            if (f > 0) {
                entity.setFlameAddend(f);
            }
            //JITL.LOGGER.info(stack.getDamageValue());

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

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.MENDING ||
                enchantment == Enchantments.UNBREAKING ||
                enchantment == Enchantments.SHARPNESS ||
                enchantment == JEnchantments.LIGHTWEIGHT.get() ||
                enchantment == JEnchantments.AMBIT.get() ||
                enchantment == JEnchantments.SCORCHING.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        //how to fuck do we add a damage tooltip to this?
        // tooltip.add(new TranslationTextComponent("damage").append(damage + ""));
    }
}
