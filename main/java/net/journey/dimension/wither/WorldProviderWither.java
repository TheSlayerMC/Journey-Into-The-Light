package net.journey.dimension.wither;

import net.journey.dimension.DimensionHelper;
import net.journey.dimension.frozen.ChunkProviderFrozenLands;
import net.journey.util.Config;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderWither extends WorldProvider {

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.wither, 0.0F);
		this.dimensionId = Config.wither;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        return new Vec3(1F, 1F, 1F);
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
    	return true;
    }
	
	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;

	}
	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return "Wither";
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
        return new ChunkProviderWither(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().getGeneratorOptions());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.5F; 
	}
    
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getDimensionName() {
		return "Withanian Lands";
	}

	@Override
	public String getInternalNameSuffix() {
		return "Withanian";
	}
}