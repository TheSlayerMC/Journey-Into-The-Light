package net.jitl.common.world.gen.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.NetherCappedSurfaceBuilder;

public class TestSurfaceBuilder extends NetherCappedSurfaceBuilder {
    private static final BlockState LAVAROCK = JBlocks.BLOOD_ROCK.defaultBlockState();
    private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    private static final ImmutableList<BlockState> FLOOR_BLOCK_STATES = ImmutableList.of(LAVAROCK, BLACKSTONE);
    private static final ImmutableList<BlockState> CEILING_BLOCK_STATES = ImmutableList.of(LAVAROCK);

    public TestSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    @Override
    protected ImmutableList<BlockState> getFloorBlockStates() {
        return FLOOR_BLOCK_STATES;
    }

    @Override
    protected ImmutableList<BlockState> getCeilingBlockStates() {
        return CEILING_BLOCK_STATES;
    }

    @Override
    protected BlockState getPatchBlockState() {
        return GRAVEL;
    }
}
