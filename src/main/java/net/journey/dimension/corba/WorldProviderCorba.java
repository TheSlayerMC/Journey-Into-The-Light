package net.journey.dimension.corba;

import com.google.common.collect.Lists;
import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.BiomeProviderMultiple;
import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class WorldProviderCorba extends BaseWorldProvider {

	public WorldProviderCorba() {
        super(new BiomeProviderMultiple(new WorldInfoCorba(), Lists.newArrayList(DimensionHelper.CORBA_BIOME), Lists.newArrayList(DimensionHelper.CORBA_PLAINS_BIOME)), new Vec3d(0.5, 0.55, 0));
    }

	@Override
	public void init() {
		this.nether = false;
        this.hasSkyLight = true;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }

    @Override
    public @NotNull IChunkGenerator createChunkGenerator() {
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
    public @NotNull DimensionType getDimensionType() {
        return DimensionHelper.CORBA_DIM;
    }

}