package net.jitl.common.world.gen.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

public class TestSurfaceBuilder extends ValleySurfaceBuilder {
	private static final BlockState LAVAROCK = JBlocks.LAVA_ROCK.defaultBlockState();
	private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
	private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
	private static final ImmutableList<BlockState> FLOOR_BLOCK_STATES = ImmutableList.of(LAVAROCK, BLACKSTONE);
	private static final ImmutableList<BlockState> CEILING_BLOCK_STATES = ImmutableList.of(LAVAROCK);

	public TestSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
		super(codec);
	}

	protected ImmutableList<BlockState> getFloorBlockStates() {
		return FLOOR_BLOCK_STATES;
	}

	protected ImmutableList<BlockState> getCeilingBlockStates() {
		return CEILING_BLOCK_STATES;
	}

	protected BlockState getPatchBlockState() {
		return GRAVEL;
	}
}
