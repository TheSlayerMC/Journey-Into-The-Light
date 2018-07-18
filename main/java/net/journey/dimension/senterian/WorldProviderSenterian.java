package net.journey.dimension.senterian;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderSenterian extends WorldProvider {

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.senterian, 0.0F);
		this.dimensionId = Config.senterian;
		isHellWorld = true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        return new Vec3(0.2, 0.1, 0);
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
		return "Senterian";
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderSenterian(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.6F; 
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getDimensionName() {
		return "Senterian Labyrinth";
	}

	@Override
	public String getInternalNameSuffix() {
		return "Senterian Labyrinth";
	}
}