package net.journey.dimension.euca;

import com.google.common.collect.Lists;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.base.biome.BiomeProviderMultiple;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class WorldProviderEuca extends BaseWorldProvider {

    private static final List<Biome> COMMON_BIOMES = Lists.newArrayList(DimensionHelper.EUCA_GOLD_BIOME, DimensionHelper.EUCA_GOLDITE_GRAINS_BIOME);
    private static final List<Biome> RARE_BIOMES = Lists.newArrayList(DimensionHelper.EUCA_SILVER_BIOME);

    public WorldProviderEuca() {
        super(world -> new BiomeProviderMultiple(world.getWorldInfo(), COMMON_BIOMES, RARE_BIOMES), new Vec3d(1.28, 1.15, 0.7));
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
        return 5.0F;
    }

    @Override
    public @NotNull IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorEuca(world, world.getSeed());
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float calculateCelestialAngle(long var1, float var3) {
        return 0.15F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public @NotNull DimensionType getDimensionType() {
        return DimensionHelper.EUCA_DIM;
    }
}