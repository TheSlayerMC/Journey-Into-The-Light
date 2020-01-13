package net.journey.dimension.boil;

import net.journey.dimension.DimensionHelper;
import net.journey.dimension.base.BaseWorldProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderBoiling extends BaseWorldProvider {

    public WorldProviderBoiling() {
        super(new BiomeProviderSingle(DimensionHelper.boiling), getSkyRender(), new Vec3d(0.2, 0.1, 0));
    }

    private static IRenderHandler getSkyRender() {
//    	return new BoilSkyRenderer();
        return null;
    }

    @Override
    public void init() {
        nether = true;
        hasSkyLight = true;
    }

    @Override
    public String getSaveFolder() {
        return "Boiling Point";
    }

    @Override
    public float getCloudHeight() {
        return 128.0F;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderBoiling(this.world, this.world.getSeed());
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
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long var1, float var3) {
        return 0.25F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionHelper.boilingType;
    }
}