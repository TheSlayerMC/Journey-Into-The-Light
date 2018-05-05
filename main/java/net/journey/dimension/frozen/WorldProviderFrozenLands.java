package net.journey.dimension.frozen;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderFrozenLands extends WorldProvider{

	@Override
	public void registerWorldChunkManager(){
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.frozen, 0.8f);
		this.dimensionId = Config.frozen;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderFrozenLands(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().getGeneratorOptions());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 100;
    }
	
	@Override
	public String getDimensionName() {
		return "Frozen Lands";
	}
	
	@Override
	public boolean canRespawnHere(){
		return false;
	}

	@Override
	public String getInternalNameSuffix() {
		return "FrozenLands";
	}
}
