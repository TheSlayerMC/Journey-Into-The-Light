package net.journey.dimension.frozen;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderFrozenLands extends WorldProvider{

	@Override
	public void init(){
		this.biomeProvider = new BiomeProviderSingle(DimensionHelper.euca);
		this.nether = false;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderFrozenLands(world, world.getSeed(), world.getWorldInfo().getGeneratorOptions());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 100;
    }

	@Override
	public boolean canRespawnHere(){
		return false;
	}
	
	@Override
	public String getSaveFolder() {
		return "Frozenlands";
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionHelper.frozenType;
	}
}
