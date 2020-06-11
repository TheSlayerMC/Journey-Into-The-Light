package net.journey.dimension.euca;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.base.gen.BiomeProviderMultiple;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class WorldProviderEuca extends BaseWorldProvider {

    public WorldProviderEuca() {
        super(new BiomeProviderMultiple(new WorldInfoEuca()), new Vec3d(1.28, 1.15, 0.7));
    }

    @Override
    public void init() {
        this.nether = false;
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderMultiple(this.world.getWorldInfo()) {
            @Override
            public List<Biome> getBiomesToSpawnIn() {
                return Arrays.asList(new Biome[] { DimensionHelper.EUCA_BIOME, DimensionHelper.EUCA_SILVER_BIOME });
            }
        };
    }

    @Override
    public String getSaveFolder() {
        return "Euca";
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }
    
    @Override
    public float getCloudHeight() {
        return 94.0F;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderEuca(world, world.getSeed());
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float calculateCelestialAngle(long var1, float var3) {
        return 0.10F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionHelper.EUCA_DIM;
    }
}