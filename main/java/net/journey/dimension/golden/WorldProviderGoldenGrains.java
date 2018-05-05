package net.journey.dimension.golden;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderGoldenGrains extends WorldProvider {
	
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.corba, 0.5F);
		this.dimensionId = Config.corba;
	}
	
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderGoldenGrains(this.worldObj, this.worldObj.getSeed());
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float f1, float f2) {
    	return new Vec3(1.3, 1.5, 0.0);
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
    public String getDimensionName() {
        return "Golden Grains";
    }

	@Override
	public String getSaveFolder() {
		return getDimensionName();
	}

	@Override
	public String getInternalNameSuffix() {
		return getDimensionName();
	}
}