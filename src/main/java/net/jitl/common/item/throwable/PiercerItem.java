package net.jitl.common.item.throwable;

import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.init.JEnchantments;
import net.jitl.util.TriFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PiercerItem extends Item {
    protected TriFunction<Level, LivingEntity, ItemStack, PiercerEntity> projectileFactory;

    public PiercerItem(Properties properties, TriFunction<Level, LivingEntity, ItemStack, PiercerEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
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
                entity.setRangeAddend(k * 4);
            }

            int f = EnchantmentHelper.getItemEnchantmentLevel(JEnchantments.SCORCHING.get(), stack);
            if (f > 0) {
                entity.setFlameAddend(f);
            }

            int a = EnchantmentHelper.getItemEnchantmentLevel(JEnchantments.FAITHFUL.get(), stack);
            if (a > 0) {
                entity.setFaithfulLevel(a);
            }
            //JITL.LOGGER.info(stack.getDamageValue());

            entity.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
            entity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            if (playerIn.isCreative()) {
                entity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            } else {
                playerIn.getInventory().removeItem(stack);
            }
            worldIn.addFreshEntity(entity);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.sidedSuccess(stack, worldIn.isClientSide());
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
                enchantment == JEnchantments.FAITHFUL.get() ||
                enchantment == JEnchantments.SCORCHING.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        //how to fuck do we add a damage tooltip to this?
        // tooltip.add(new TranslationTextComponent("damage").append(damage + ""));
    }
}
