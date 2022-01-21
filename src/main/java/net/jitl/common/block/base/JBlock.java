package net.jitl.common.block.base;

import net.jitl.core.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

public class JBlock extends Block {

    private final BlockPropsFactory blockP;

    public JBlock(BlockPropsFactory properties) {
        super(properties.create());
        this.blockP = properties;
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return blockP == JBlockProperties.HOLD_FIRE || super.isFireSource(state, world, pos, side);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return blockP == JBlockProperties.HOLD_FIRE || blockP == JBlockProperties.HOLD_FIRE_SAND;
    }
}
