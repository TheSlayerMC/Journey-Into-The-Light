package net.jitl.common.item;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.entity.projectile.CalciaMineEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;

import java.util.Random;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.hurt(new DamageSource("test"), 10);

        return ActionResult.success(playerIn.getItemInHand(handIn));
    }


    public boolean isInMainHand(PlayerEntity player) {
        return player.getItemInHand(Hand.MAIN_HAND).getItem() == this;
    }
}
