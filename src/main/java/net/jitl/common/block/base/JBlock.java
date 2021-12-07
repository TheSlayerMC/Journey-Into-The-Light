package net.jitl.common.block.base;

import net.jitl.util.JBlockProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

public class JBlock extends Block {

    private final BlockPropsFactory blockP;

    public JBlock(BlockPropsFactory properties) {
        super(properties.create());
        this.blockP = properties;
    }

    @Override
    public boolean isFireSource(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return blockP == JBlockProperties.HOLD_FIRE ? true : super.isFireSource(state, world, pos, side);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return blockP == JBlockProperties.HOLD_FIRE || blockP == JBlockProperties.HOLD_FIRE_SAND;
    }
}
