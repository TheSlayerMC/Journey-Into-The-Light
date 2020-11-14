package net.jitl.common.item;

import net.jitl.common.world.gen.util.GenHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

        return ActionResult.success(playerIn.getItemInHand(handIn));
    }
}
