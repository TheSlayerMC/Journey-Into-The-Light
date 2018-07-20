package net.journey.dimension.cloudia;

import net.journey.dimension.DimensionHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCloudia extends WorldProvider {
	
	@Override
	public void init() {
		this.biomeProvider = new BiomeProviderSingle(DimensionHelper.euca);
		this.nether = false;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderCloudia(this.world, this.world.getSeed());
	}

    @Override
	public int getMoonPhase(long p_76559_1_)
    {
        return (int)(p_76559_1_ / 24000L % 8L + 8L) % 8;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float f1, float f2) {
    	return new Vec3d(1.7, 0.7, 1.2333);
    }

	@Override
    public boolean canRespawnHere() {
        return false;
    }

	@Override
    public boolean isSurfaceWorld() {
        return false;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 8.0F;
    }

	@Override
    public int getAverageGroundLevel() {
        return 70;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }

	@Override
	public DimensionType getDimensionType() {
		return DimensionHelper.cloudiaType;
	}
}