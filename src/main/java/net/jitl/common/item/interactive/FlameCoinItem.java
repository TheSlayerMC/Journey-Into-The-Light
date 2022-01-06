package net.jitl.common.item.interactive;

import net.jitl.init.JBlocks;
import net.jitl.init.JSounds;
import net.jitl.util.JItemProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class FlameCoinItem extends Item {
    public FlameCoinItem() {
        super(JItemProperties.miscGrouped());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if (player != null) {
            for (Direction direction : Direction.Plane.VERTICAL) {
                BlockPos blockpos = context.getClickedPos();
                BlockState blockstate = world.getBlockState(blockpos);
                if (blockstate.getBlock() == JBlocks.EUCA_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.EUCA_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                }
                if (blockstate.getBlock() == JBlocks.FROZEN_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.FROZEN_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                }
                if (blockstate.getBlock() == JBlocks.BOIL_PORTAL_FRAME && context.getClickedFace() == Direction.UP) {
                    if (JBlocks.BOIL_PORTAL.makePortal(context.getLevel(), blockpos.relative(direction))) {
                        context.getLevel().playSound(null, blockpos, JSounds.GLUMP_DEATH.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                }
            }
            if (!player.isCreative()) context.getItemInHand().shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
