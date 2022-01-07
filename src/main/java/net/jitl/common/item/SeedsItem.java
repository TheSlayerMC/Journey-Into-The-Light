package net.jitl.common.item;

import net.jitl.util.JItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SeedsItem extends Item {

    private final Block cropBlock;

    public SeedsItem(Block crop) {
        super(JItemProperties.miscGrouped());
        this.cropBlock = crop;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        BlockPos blockpos = context.getClickedPos();
        if(context.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == Blocks.FARMLAND && context.getClickedFace() == Direction.UP) {
                if(!world.isClientSide) {
                    world.setBlock(blockpos.above(), cropBlock.defaultBlockState(), 11);
                    if(!player.isCreative()) context.getItemInHand().shrink(1);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
}
