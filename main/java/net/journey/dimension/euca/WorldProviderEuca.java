package net.journey.dimension.euca;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderEuca extends WorldProvider {

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.euca, 0.5F);
        this.dimensionId = Config.euca;
        isHellWorld = false;
    }
    
    @Override
    public String getSaveFolder() {
    	return "Euca";
    }

    @Override
    public float getCloudHeight() {
        return 128.0F;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void setSkyRenderer(IRenderHandler skyRenderer) {
    	super.setSkyRenderer(new EucaSkyRenderer());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float f1, float f2) {
    	return new Vec3(1.5, 1.1, 1);
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderEuca(worldObj, worldObj.getSeed());
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public float calculateCelestialAngle(long var1, float var3) {
        return 0.1F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

	@Override
	public DimensionType getDimensionType() {
		return DimensionHelper.eucaType;
	}
}