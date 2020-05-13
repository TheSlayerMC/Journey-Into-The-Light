package net.journey.dimension.boil;

import javax.annotation.Nullable;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.journey.proxy.ClientProxy;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderBoiling extends BaseWorldProvider {

    public WorldProviderBoiling() {
        super(new BiomeProviderSingle(DimensionHelper.boiling), new Vec3d(0.2, 0.1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return ClientProxy.boilSkyRenderer;
    }

    @Override
    public void init() {
        nether = true;
        hasSkyLight = true;
        doesWaterVaporize = true;
    }
    
    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }

    @Override
    public String getSaveFolder() {
        return "Boiling Point";
    }

    @Override
    public float getCloudHeight() {
        return 64F;
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