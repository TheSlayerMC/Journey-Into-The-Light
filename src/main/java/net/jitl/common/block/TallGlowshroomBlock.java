package net.jitl.common.block;

import net.jitl.common.block.base.JDoublePlantBlock;
import net.jitl.core.api.block.GroundPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TallGlowshroomBlock extends JDoublePlantBlock {

    private static final VoxelShape HITBOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

    public TallGlowshroomBlock(Properties properties) {
        super(properties);
        setGroundPredicate(GroundPredicate.UNDERGROUND);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return HITBOX;
    }
}