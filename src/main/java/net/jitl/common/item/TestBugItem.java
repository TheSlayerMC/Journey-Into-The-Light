package net.jitl.common.item;

import net.jitl.init.JSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.GenHelper;
import ru.timeconqueror.timecore.api.util.RandHelper;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide()) {

            GenHelper.genHollowCircle(10, Direction.Axis.Y, blockPos -> {
                worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).offset(blockPos), Blocks.DIAMOND_BLOCK.defaultBlockState());
            });

            GenHelper.genFilledCircle(10, Direction.Axis.Y, pos -> {
                worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).above().offset(pos), Blocks.STONE.defaultBlockState());
            });
        }

        if (worldIn.isClientSide()) {
            SoundEvent soundEvent = RandHelper.chooseEqually(JSounds.MUD_BLOCK_BREAK.get(), JSounds.MUD_BLOCK_DIG.get());
            worldIn.playSound(playerIn, playerIn.blockPosition(), soundEvent, SoundCategory.MASTER, 1.0F, 1.0F);
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }
}
