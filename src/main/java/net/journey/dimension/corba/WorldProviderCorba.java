package net.journey.dimension.corba;

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

public class WorldProviderCorba extends BaseWorldProvider {

    public WorldProviderCorba() {
        super(new BiomeProviderSingle(DimensionHelper.corba), new Vec3d(0.5, 0.55, 0));
    }

    @Override
    public void init() {
        this.nether = false;
        hasSkyLight = true;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }
    
    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderCorba(this.world, this.world.getSeed());
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
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 8.0F;
    }

    @Override
    public int getAverageGroundLevel() {
        return 63;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public String getSaveFolder() {
        return "Corba";
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionHelper.corbaType;
    }

}