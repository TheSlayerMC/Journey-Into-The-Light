package net.jitl.common.item.food;

import net.jitl.init.JSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CrystalAppleItem extends Item {
    public CrystalAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull LivingEntity entityLiving) {
        super.finishUsingItem(stack, worldIn, entityLiving);
        if (entityLiving instanceof PlayerEntity) {
            worldIn.playSound((PlayerEntity) entityLiving, entityLiving.blockPosition(), JSounds.CRYSTAL_APPLE_FREEZE.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
        return stack;
    }
}
