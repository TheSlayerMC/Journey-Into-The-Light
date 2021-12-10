package net.jitl.common.block.base;

import net.jitl.common.block.trees.JTree;
import net.minecraft.block.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class JSaplingBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final JTree treeGrower;

    public JSaplingBlock(JTree treeIn, AbstractBlock.Properties properties) {
        super(properties);
        this.treeGrower = treeIn;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            if (!worldIn.isAreaLoaded(pos, 1))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            this.advanceTree(worldIn, pos, state, random);
        }

    }

    public void advanceTree(ServerWorld worldIn, BlockPos pos, BlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            worldIn.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(worldIn, rand, pos)) return;
            this.treeGrower.growTree(worldIn, worldIn.getChunkSource().getGenerator(), pos, state, rand);
        }

    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double) worldIn.random.nextFloat() < 0.45D;
    }

    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.advanceTree(worldIn, pos, state, rand);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
