package net.journey.dimension.terrania;

import com.google.common.collect.Lists;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.BiomeProviderMultiple;
import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.journey.proxy.ClientProxy;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class WorldProviderTerrania extends BaseWorldProvider {

    private static final List<Biome> COMMON_BIOMES = Lists.newArrayList(DimensionHelper.TERRANIA_BIOME);
    private static final List<Biome> RARE_BIOMES = Lists.newArrayList(DimensionHelper.ENCHANTED_SHROOM_FOREST_BIOME);
	
	public WorldProviderTerrania() {
		super(world -> new BiomeProviderMultiple(world.getWorldInfo(), COMMON_BIOMES, RARE_BIOMES)/*, new Vec3d(0.7, 0.1, 0.65)*/);
    }
    
    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return ClientProxy.terraniaSkyRenderer;
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
        return "Terrania";
    }

    @Override
    public float getCloudHeight() {
        return 128.0F;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorTerrania(this.world, this.world.getSeed());
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
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
	    return DimensionHelper.TERRANIA_DIM;
    }
}