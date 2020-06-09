package net.journey.dimension.corba;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.base.gen.BiomeProviderMultiple;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class WorldProviderCorba extends BaseWorldProvider {

	public WorldProviderCorba() {
		super(new BiomeProviderMultiple(new WorldInfoCorba()), new Vec3d(0.5, 0.55, 0));
	}

	@Override
	public void init() {
		this.nether = false;
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderMultiple(this.world.getWorldInfo()) {
            @Override
            public List<Biome> getBiomesToSpawnIn() {
                return Arrays.asList(new Biome[] { DimensionHelper.CORBA_BIOME, DimensionHelper.CORBA_PLAINS_BIOME });
            }
        };
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }
    
    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderCorba(this.world, this.world.getSeed(), world.getWorldInfo().getGeneratorOptions());
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
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 128.0F;
    }

    @Override
    public int getAverageGroundLevel() {
        return 63;
    }

    @Override
    public String getSaveFolder() {
        return "Corba";
    }

    @Override
    public DimensionType getDimensionType() {
	    return DimensionHelper.CORBA_DIM;
    }

}