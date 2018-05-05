package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.boil.gen.WorldGenBoilingLamp;
import net.journey.dimension.boil.gen.WorldGenBoilingLava;
import net.journey.dimension.boil.gen.WorldGenBrisonNetwork;
import net.journey.dimension.boil.gen.WorldGenTraderHutBoiling;
import net.journey.dimension.boil.gen.WorldGenVolcano;
import net.journey.dimension.boil.trees.WorldGenBoilTree1;
import net.journey.dimension.boil.trees.WorldGenBoilTree2;
import net.journey.dimension.boil.trees.WorldGenBoilTree3;
import net.journey.dimension.corba.gen.WorldGenCorbaLamp;
import net.journey.dimension.corba.gen.WorldGenCorbaVillage;
import net.journey.dimension.corba.gen.WorldGenTreehouse;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaHugeTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaLargeTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaMediumTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSmallTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSpruceTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSpruceTree1;
import net.journey.dimension.corba.gen.trees.WorldGenHugeCorbaSpruceTree;
import net.journey.dimension.euca.gen.WorldGenSmeltery;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree3;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaBigTree3;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.slayer.api.block.BlockModFlower;

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
		BiomeGenBase biomegenbase = this.w.getBiomeGenForCoords(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getStrongholdGen(World world, String par2, BlockPos par3) {
		return null;
	}
}