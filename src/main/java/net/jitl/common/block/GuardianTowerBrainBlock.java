package net.jitl.common.block;

import net.jitl.init.JItems;
import net.jitl.init.JTiles;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.client.ClientProxy;

import java.util.Random;

public class GuardianTowerBrainBlock extends Block {
    public GuardianTowerBrainBlock() {
        super(JBlockProperties.UNBREAKABLE_UTILITY.create());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return JTiles.GUARDIAN_TOWER_BRAIN.create();
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);

        if (JItems.TEST_BUG.isInMainHand(ClientProxy.player())) {
            worldIn.addParticle(ParticleTypes.BARRIER, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }
    }
}
