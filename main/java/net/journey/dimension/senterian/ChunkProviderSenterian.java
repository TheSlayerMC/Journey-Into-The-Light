package net.journey.dimension.senterian;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderSenterian implements IChunkProvider {

	private World w;
	
	public ChunkProviderSenterian(World world, long seed) {
		this.w = world;
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public boolean func_177460_a(IChunkProvider provider, Chunk chunk, int par3, int par4) {
		return false;
	}
	
	@Override
	public Chunk provideChunk(int x, int z)  {
		Chunk chunk = new Chunk(w, new ChunkPrimer(), x, z);
		chunk.generateSkylightMap();
		return chunk;
	}
	
	@Override
	public Chunk provideChunk(BlockPos pos) {
		return provideChunk(pos.getX(), pos.getZ());
	}
	
	@Override
	public void populate(IChunkProvider icp, int x, int z) {}

	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate update) {
		return true;
	}
	
	@Override
	public void recreateStructures(Chunk ch, int x, int z) {}

	@Override
	public void saveExtraData() {}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}
	
	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome Biome = this.w.getBiomeGenForCoords(pos);
		return Biome.getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getStrongholdGen(World world, String par2, BlockPos par3) {
		return null;
	}
}