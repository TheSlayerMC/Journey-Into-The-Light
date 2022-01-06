package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.EssenciaAltarTile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import ru.timeconqueror.timecore.api.util.WorldUtils;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EssenciaAltarBlock extends JTileContainerBlock {
    public EssenciaAltarBlock(Properties properties) {
        super(properties, (blockState, iBlockReader) -> new EssenciaAltarTile());
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide() && handIn == InteractionHand.MAIN_HAND && worldIn.isNight()) {
            WorldUtils.forTileWithReqt(worldIn, pos, EssenciaAltarTile.class, tile -> {
                tile.onRightClick(((ServerPlayer) player), player.getItemInHand(handIn));
            });
        }

        return InteractionResult.sidedSuccess(worldIn.isClientSide());
    }

    /*@Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        WorldUtils.forTileWithReqt(worldIn, pos, EssenciaAltarTile.class, tile -> {
            if (tile.isActivated()) {
                for (int i = 0; i < 2; i++) {
                    IParticleData particle = JParticleManager.ESSENCIA_LIGHTNING.get();
                    int posThreshold = 16;
                    float posRandom0 = (float) rand.nextInt(5 + i) / posThreshold;
                    float posRandom1 = (float) rand.nextInt(5 + i) / posThreshold;
                    float posRandom2 = (float) rand.nextInt(5 + i) / posThreshold;
                    float posRandom3 = (float) rand.nextInt(5 + i) / posThreshold;
                    worldIn.addParticle(particle, (pos.getX() + posRandom0) - 2.5F, pos.getY() + posRandom0 + 2, (pos.getZ() - posRandom0) - 2.5F, 0, 0, 0);
                    worldIn.addParticle(particle, (pos.getX() - posRandom1) + 2.5F, pos.getY() - posRandom1 + 2, (pos.getZ() + posRandom1) + 2.5F, 0, 0, 0);
                    worldIn.addParticle(particle, (pos.getX() - posRandom2) - 2.5F, pos.getY() + posRandom2 + 2, (pos.getZ() - posRandom2) + 2.5F, 0, 0, 0);
                    worldIn.addParticle(particle, (pos.getX() + posRandom3) + 2.5F, pos.getY() - posRandom3 + 2, (pos.getZ() + posRandom3) - 2.5F, 0, 0, 0);
                }
            }
        });
    } */
}
