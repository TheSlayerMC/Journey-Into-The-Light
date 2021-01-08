package net.jitl.common.block;

import net.jitl.api.block.GroundPredicate;
import net.jitl.common.block.base.JDoublePlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

public class TallGlowshroomBlock extends JDoublePlantBlock {

    private static final VoxelShape HITBOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

    public TallGlowshroomBlock(Properties properties) {
        super(properties);
        setGroundPredicate(GroundPredicate.UNDERGROUND);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return HITBOX;
    }
}