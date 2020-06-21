package net.journey.dimension.frozen;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.journey.init.JourneySounds;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class WorldProviderFrozenLands extends BaseWorldProvider {

	public WorldProviderFrozenLands() {
        super(world1 -> new BiomeProviderSingle(DimensionHelper.FROZEN_BIOME));
    }
    
    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public MusicTicker.MusicType getMusicType() {
        return EnumHelperClient.addMusicType("null", JourneySounds.EMPTY, 0, 1);
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return true;

    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorFrozenLands(world, world.getSeed(), world.getWorldInfo().getGeneratorOptions());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 100;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public String getSaveFolder() {
        return "Frozenlands";
    }

    @Override
    public DimensionType getDimensionType() {
	    return DimensionHelper.FROZEN_DIM;
    }
}
