package net.jitl.common.block.base;

import net.jitl.init.JBlocks;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

public class JBlockStalagmite extends Block {

    protected static final VoxelShape BIG_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape MED_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape SMALL_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final VoxelShape TINY_SHAPE = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);

    public JBlockStalagmite() {
        super(JBlockProperties.STONE_PROPS.create());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return this == JBlocks.SCORCHED_STALAGMITE_LARGE ? BIG_SHAPE : this == JBlocks.SCORCHED_STALAGMITE_MED ? MED_SHAPE :
                this == JBlocks.SCORCHED_STALAGMITE_SMALL ? SMALL_SHAPE : this == JBlocks.SCORCHED_CACTUS ? BIG_SHAPE : TINY_SHAPE;
    }

}
