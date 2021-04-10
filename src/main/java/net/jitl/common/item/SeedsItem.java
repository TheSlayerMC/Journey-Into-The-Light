package net.jitl.common.item;

import net.jitl.init.JTabs;
import net.jitl.util.JItemProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeedsItem extends Item {

    private final Block cropBlock;

    public SeedsItem(Block crop) {
        super(JItemProperties.miscGrouped());
        this.cropBlock = crop;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        BlockPos blockpos = context.getClickedPos();
        if(context.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate != null && blockstate.getBlock() == Blocks.FARMLAND && context.getClickedFace() == Direction.UP) {
                if(!world.isClientSide) {
                    world.setBlock(blockpos.above(), cropBlock.defaultBlockState(), 11);
                    if(!player.isCreative()) context.getItemInHand().shrink(1);
                }
                return ActionResultType.sidedSuccess(world.isClientSide);
            }
        }
        return ActionResultType.PASS;
    }
}
