package net.journey.dimension.boil;

import com.google.common.collect.Lists;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.base.biome.BiomeProviderMultiple;
import net.journey.init.JourneySounds;
import net.journey.proxy.ClientProxy;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

public class WorldProviderBoiling extends BaseWorldProvider {

    private static final List<Biome> COMMON_BIOMES = Lists.newArrayList(DimensionHelper.BOILING_BIOME, DimensionHelper.BOILING_SANDS_BIOME);
    private static final List<Biome> RARE_BIOMES = Lists.newArrayList(DimensionHelper.CHARRED_FIELDS_BIOME, DimensionHelper.SCORCHED_WASTELAND_BIOME);

    public WorldProviderBoiling() {
        super(world1 -> new BiomeProviderMultiple(world1.getWorldInfo(), COMMON_BIOMES, RARE_BIOMES), new Vec3d(0.2, 0.1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return ClientProxy.boilSkyRenderer;
    }

    @Override
    public void init() {
        super.init();

        nether = true;
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
        return new ChunkGeneratorBoiling(this.world, this.world.getSeed());
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
	    return DimensionHelper.BOILING_DIM;
    }
}