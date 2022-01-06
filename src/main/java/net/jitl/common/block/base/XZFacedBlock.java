package net.jitl.common.block.base;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

/**
 * Block that can be placed parallel to the horizontal axis.
 */
public class XZFacedBlock extends Block {
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public XZFacedBlock(Properties properties) {
        super(properties);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(HORIZONTAL_AXIS)) {
                    case Z:
                        return state.setValue(HORIZONTAL_AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(HORIZONTAL_AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_AXIS);
    }
}
