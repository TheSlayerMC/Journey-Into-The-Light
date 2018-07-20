package net.journey.dimension.corba;

import net.journey.dimension.DimensionHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCorba extends WorldProvider {
	
	@Override
	public void init() {
		this.biomeProvider = new BiomeProviderSingle(DimensionHelper.corba);
		this.nether = false;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderCorba(this.world, this.world.getSeed());
	}
	
	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.6F; 
	}
    
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float f1, float f2) {
    	return new Vec3d(0.5, 0.55, 0);
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
        return 63;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
	
	@Override
	public String getSaveFolder() {
		return "Corba";
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionHelper.corbaType;
	}

}