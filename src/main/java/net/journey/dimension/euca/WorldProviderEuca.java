package net.journey.dimension.euca;

import javax.annotation.Nullable;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderEuca extends BaseWorldProvider {

    public WorldProviderEuca() {
        super(new BiomeProviderSingle(DimensionHelper.euca), new Vec3d(1.28, 1.15, 0.7));
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
        return DimensionHelper.eucaType;
    }
}