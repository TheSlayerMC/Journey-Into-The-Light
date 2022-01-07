package net.jitl.common.block;

import net.jitl.init.JItems;
import net.jitl.init.JTiles;
import net.jitl.util.JBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.client.ClientProxy;

import java.util.Random;

public class GuardianTowerBrainBlock extends Block implements EntityBlock {

    public GuardianTowerBrainBlock() {
        super(JBlockProperties.UNBREAKABLE_UTILITY.create());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos_, BlockState state_) {
        return JTiles.GUARDIAN_TOWER_BRAIN.create(pos_, state_);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);

        if (JItems.TEST_BUG.isInMainHand(ClientProxy.player())) {
            worldIn.addParticle(ParticleTypes.HEART, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }
    }
}
