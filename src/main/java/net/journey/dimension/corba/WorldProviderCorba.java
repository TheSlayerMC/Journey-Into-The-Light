package net.journey.dimension.corba;

import net.journey.dimension.DimensionHelper;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.boil.BiomeProviderBoil;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCorba extends BaseWorldProvider {

	public WorldProviderCorba() {
		super(new BiomeProviderSingle(DimensionHelper.corba), new Vec3d(0.5, 0.55, 0));
	}

	@Override
	public void init() {
		this.nether = false;
		hasSkyLight = true;
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