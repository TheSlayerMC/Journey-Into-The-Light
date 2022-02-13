package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.EssenciaAltarTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.WorldUtils;

public class EssenciaAltarBlock extends JTileContainerBlock {
    public EssenciaAltarBlock(Properties properties) {
        super(properties, EssenciaAltarTile::new);
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!worldIn.isClientSide() && handIn == InteractionHand.MAIN_HAND && worldIn.isNight()) {
            WorldUtils.forTileWithReqt(worldIn, pos, EssenciaAltarTile.class, tile -> tile.onRightClick(((ServerPlayer) player), player.getItemInHand(handIn)));
        }

        return InteractionResult.sidedSuccess(worldIn.isClientSide());
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, (BlockEntityType<? extends EssenciaAltarTile>) JTiles.ESSENCIA_ALTAR, EssenciaAltarTile::tick);
    }
}
