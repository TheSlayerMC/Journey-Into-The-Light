package net.journey.dimension.wastelands;
/*package net.essence.dimension.wastelands;

import net.essence.dimension.DimensionHelper;
import net.essence.util.Config;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderWastelands extends WorldProvider {
	
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.wastelands, 0.5F);
		this.dimensionId = Config.wastelands;
	}
	
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderWastelands(this.worldObj, this.worldObj.getSeed());
	}
	
	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.4F;
	}
    
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float f1, float f2) {
    	return new Vec3(1.8, 1.15, 1.0);
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
        return true;
    }

	@Override
    public String getDimensionName() {
        return "Wastelands";
    }

	@Override
	public String getSaveFolder() {
		return getDimensionName();
	}

	@Override
	public String getInternalNameSuffix() {
		return getDimensionName();
	}
}*/