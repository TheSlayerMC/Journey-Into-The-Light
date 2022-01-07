package net.jitl.common.block;

import net.jitl.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AncientSocketBlock extends Block {

    public static final BooleanProperty INSERTED = BooleanProperty.create("insert");
    protected static final VoxelShape BLOCK = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape INSERT = Block.box(4.0D, 16.0D, 4.0D, 12.0D, 19.0D, 12.0D);
    protected static final VoxelShape FULL_SHAPE = Shapes.or(BLOCK, INSERT);

    public AncientSocketBlock() {
        super(JBlockProperties.DUNGEON_BLOCK_PROPS.create());
        this.registerDefaultState(this.stateDefinition.any().setValue(INSERTED, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(INSERTED) ? FULL_SHAPE : BLOCK;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(INSERTED, Boolean.valueOf(false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(INSERTED);
    }
}
