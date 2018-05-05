package net.journey.dimension.depths;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderDepths extends WorldProvider {

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.depths, 0.5F);
        this.dimensionId = Config.depths;
        isHellWorld = false;
    }
    
    @Override
    public String getSaveFolder() {
    	return "Depths";
    }

    @Override
    public float getCloudHeight() {
        return 128.0F;
    }
    
    @Override
    public int getHeight() {
        return 128;
    }

    @Override
    public int getActualHeight() {
        return 128;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderDepths(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long var1, float var3) {
        return 0.3F; 
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
    
    @Override
    public String getDimensionName() {
        return "The Depths";
    }

	@Override
	public String getInternalNameSuffix() {
		return "Depths";
	}
}