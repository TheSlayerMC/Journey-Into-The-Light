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
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkProviderSenterian implements IChunkGenerator {

	private World w;
	
	public ChunkProviderSenterian(World world, long seed) {
		this.w = world;
	}
	
	@Override
	public Chunk generateChunk(int x, int z)  {
		Chunk chunk = new Chunk(w, new ChunkPrimer(), x, z);
		chunk.generateSkylightMap();
		return chunk;
	}
	
	@Override
	public void recreateStructures(Chunk ch, int x, int z) {}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.w.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void populate(int x, int z) { }

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}