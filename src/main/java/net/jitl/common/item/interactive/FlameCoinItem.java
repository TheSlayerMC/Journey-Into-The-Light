package net.jitl.common.item.interactive;

import net.jitl.init.JBlocks;
import net.jitl.util.JItemProperties;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlameCoinItem extends Item {
    public FlameCoinItem() {
        super(JItemProperties.miscGrouped());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        PlayerEntity player = context.getPlayer();
        BlockState blockstate = world.getBlockState(blockpos);
        if(blockstate != null && blockstate.getBlock() == JBlocks.EUCA_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
            System.out.println("CLICKED");
            JBlocks.EUCA_PORTAL.makePortal(world, blockpos);
        }

        if(!player.isCreative()) context.getItemInHand().shrink(1);

        return ActionResultType.SUCCESS;
    }
}
