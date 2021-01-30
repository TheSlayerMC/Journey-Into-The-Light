package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.EssenciaAltarTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.WorldUtils;

public class EssenciaAltarBlock extends JTileContainerBlock {
    public EssenciaAltarBlock(Properties properties) {
        super(properties, (blockState, iBlockReader) -> new EssenciaAltarTile());
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide() && handIn == Hand.MAIN_HAND) {
            WorldUtils.forTileWithReqt(worldIn, pos, EssenciaAltarTile.class, tile -> {
                tile.onRightClick(((ServerPlayerEntity) player), player.getItemInHand(handIn));
            });
        }

        return ActionResultType.sidedSuccess(worldIn.isClientSide());
    }
}
