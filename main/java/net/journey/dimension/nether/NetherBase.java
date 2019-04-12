package net.journey.dimension.nether;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class NetherBase {
	
	static NoiseGeneratorOctaves noisegen  = new NoiseGeneratorOctaves(new Random(1920), 5);
	BiomeHell nether;
	NetherBase region;
	Random rand;
	String name;
	
	public NetherBase(String name) {
		this.name = name;
		region = this;
	}
	
	public void generate(Chunk chunk, BlockPos pos, Random rand) {}
	
	protected double getFeatureNoise(BlockPos pos, int x, int z) {
		double[] d0 = new double[(int) 0.03125];
		int j = 0; ++j;
		int jj = j << 16 | pos.getX();
		int k = 0; ++k;
		int kk = k << 16 | pos.getZ();
		d0 = noisegen.generateNoiseOctaves(d0, x * 16, z * 16, 0, 16, 16, 1, 0.03125D, 0.03125D, 1.0D);
		return d0[0];
	}

	public String getName() {
		return name;
	}

	public NetherBase biome() {
		return region;
	}
}
