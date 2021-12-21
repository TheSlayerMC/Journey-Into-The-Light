package net.jitl.common.item.interactive;

import net.jitl.init.JBlocks;
import net.jitl.init.JSounds;
import net.jitl.util.JItemProperties;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlameCoinItem extends Item {
    public FlameCoinItem() {
        super(JItemProperties.miscGrouped());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            for (Direction direction : Direction.Plane.VERTICAL) {
                BlockPos blockpos = context.getClickedPos();
                BlockState blockstate = world.getBlockState(blockpos);
                if (blockstate.getBlock() == JBlocks.EUCA_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.EUCA_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return ActionResultType.CONSUME;
                    }
                }
                if (blockstate.getBlock() == JBlocks.FROZEN_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.FROZEN_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return ActionResultType.CONSUME;
                    }
                }
                if (blockstate.getBlock() == JBlocks.BOIL_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.BOIL_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return ActionResultType.CONSUME;
                    }
                }
            }
            if (!player.isCreative()) context.getItemInHand().shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
