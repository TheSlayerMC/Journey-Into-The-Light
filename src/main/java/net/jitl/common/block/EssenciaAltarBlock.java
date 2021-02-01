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
        if (!worldIn.isClientSide() && handIn == Hand.MAIN_HAND && worldIn.isNight()) {
            WorldUtils.forTileWithReqt(worldIn, pos, EssenciaAltarTile.class, tile -> {
                tile.onRightClick(((ServerPlayerEntity) player), player.getItemInHand(handIn));
            });
        }

        return ActionResultType.sidedSuccess(worldIn.isClientSide());
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
