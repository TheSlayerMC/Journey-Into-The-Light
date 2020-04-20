package net.journey.dimension.frozen;

import net.journey.dimension.base.BaseWorldProvider;
import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderFrozenLands extends BaseWorldProvider {

    public WorldProviderFrozenLands() {
        super(new BiomeProviderSingle(DimensionHelper.frozen));
    }

    @Override
    public void init() {
        this.nether = false;
        this.hasSkyLight = true;
    }
	
    /*@Override
    public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
        return true;
    }*/

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return true;

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
    public boolean canRespawnHere() {
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
