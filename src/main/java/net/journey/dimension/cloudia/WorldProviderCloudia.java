package net.journey.dimension.cloudia;

import net.journey.dimension.DimensionHelper;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.boil.BiomeProviderBoil;
import net.journey.dimension.boil.BoilSkyRenderer;
import net.journey.proxy.ClientProxy;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCloudia extends BaseWorldProvider {

    public WorldProviderCloudia() {
        super(new BiomeProviderSingle(DimensionHelper.cloudia), new Vec3d(1.5, 1.12, 1.7));
    }

    @Override
    public void init() {
        this.nether = false;
        this.hasSkyLight = true;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer() {
		return ClientProxy.cloudiaSkyRenderer;
	}

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderCloudia(this.world, this.world.getSeed());
    }

    @Override
    public int getMoonPhase(long s) {
        return (int) (s / 24000L % 8L + 8L) % 8;
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