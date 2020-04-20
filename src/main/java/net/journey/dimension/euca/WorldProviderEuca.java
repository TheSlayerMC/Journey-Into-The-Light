package net.journey.dimension.euca;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderEuca extends BaseWorldProvider {

    public WorldProviderEuca() {
        super(new BiomeProviderSingle(DimensionHelper.euca), new Vec3d(1.2, 1.1, 0.7));
    }

    @Override
    public void init() {
        this.nether = false;
        this.hasSkyLight = true;
        //this.biomeProvider = new BiomeProviderEuca(world);
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
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderEuca(world, world.getSeed());
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
        return 0.18F;
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