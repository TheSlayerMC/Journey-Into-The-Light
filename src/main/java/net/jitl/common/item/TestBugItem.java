package net.jitl.common.item;

import net.jitl.init.JSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.util.GenHelper;

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
            worldIn.playSound(playerIn, playerIn.blockPosition(), JSounds.TEST_SOUND, SoundCategory.MASTER, 1.0F, 1.0F);
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }
}
