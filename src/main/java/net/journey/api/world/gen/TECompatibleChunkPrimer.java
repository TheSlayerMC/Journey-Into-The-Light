package net.journey.api.world.gen;

import net.journey.JITL;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Random;

public class TECompatibleChunkPrimer extends ChunkPrimer {

	private HashMap<BlockPos, PrimerData> tileEntityMap = new HashMap<>();

	public void setBlockState(int x, int y, int z, IBlockState state, @Nullable TECompatibleChunkPrimer.TileEntityInitializer<?> initializer) {
		super.setBlockState(x, y, z, state);
		if (state.getBlock().hasTileEntity(state)) {
			tileEntityMap.put(new BlockPos(x, y, z), new PrimerData(initializer)); //not a list, because if we replace block with tileentity, the last one should be also replaced.
		}
	}

	@Override
	public void setBlockState(int x, int y, int z, IBlockState state) {
		setBlockState(x, y, z, state, null);
	}

	public void addTileEntityInitializer(BlockPos pos, @Nullable TECompatibleChunkPrimer.TileEntityInitializer<?> initializer) {
		PrimerData primerData = tileEntityMap.get(pos);
		if (primerData != null) {
			primerData.tileEntityInitializer = initializer;
		} else {
			JITL.LOGGER.error("Can't apply initializer, there's no tile entity at given pos: " + pos, new Exception());
		}
	}

	public HashMap<BlockPos, PrimerData> getTileEntityMap() {
		return tileEntityMap;
	}


	public abstract static class TileEntityInitializer<T extends TileEntity> {
		private Class<T> clazz;

		public TileEntityInitializer(Class<T> clazz) {
			this.clazz = clazz;
		}

		public boolean canHandle(TileEntity tileEntity) {
			return clazz.isInstance(tileEntity);
		}

		@SuppressWarnings("unchecked")
		public void process(World world, TileEntity tileEntity, Random rand) {
			if (canHandle(tileEntity)) init(world, ((T) tileEntity), rand);
		}

		protected abstract void init(World world, T tileEntity, Random rand);
	}

	public static class PrimerData {
		private TileEntityInitializer<?> tileEntityInitializer;

		public PrimerData(@Nullable TECompatibleChunkPrimer.TileEntityInitializer<?> tileEntityInitializer) {
			this.tileEntityInitializer = tileEntityInitializer;
		}

		@Nullable
		public TECompatibleChunkPrimer.TileEntityInitializer<?> getTileEntityInitializer() {
			return tileEntityInitializer;
		}
	}
}