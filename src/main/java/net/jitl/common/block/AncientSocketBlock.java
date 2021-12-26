package net.jitl.common.block;

import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class AncientSocketBlock extends Block {

    public static final BooleanProperty INSERTED = BooleanProperty.create("insert");
    protected static final VoxelShape BLOCK = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape INSERT = Block.box(4.0D, 16.0D, 4.0D, 12.0D, 19.0D, 12.0D);
    protected static final VoxelShape FULL_SHAPE = VoxelShapes.or(BLOCK, INSERT);

    public AncientSocketBlock() {
        super(JBlockProperties.BRICK_PROPS.create());
        this.registerDefaultState(this.stateDefinition.any().setValue(INSERTED, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getValue(INSERTED) ? FULL_SHAPE : BLOCK;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(INSERTED, Boolean.valueOf(false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(INSERTED);
    }
}
