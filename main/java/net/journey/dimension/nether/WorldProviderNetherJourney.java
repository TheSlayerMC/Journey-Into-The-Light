package net.journey.dimension.nether;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderNetherJourney extends WorldProvider
{

    @Override
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(Biome.hell, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    @Override
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderNether(this.worldObj, this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getSeed());
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_)
    {
        return 0.5F;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }

    @Override
    public String getDimensionName()
    {
        return "Nether";
    }

    @Override
    public String getInternalNameSuffix()
    {
        return "_nether";
    }

    @Override
    public WorldBorder getWorldBorder()
    {
        return new WorldBorder()
        {
            @Override
			public double getCenterX()
            {
                return super.getCenterX() / 8.0D;
            }
            @Override
			public double getCenterZ()
            {
                return super.getCenterZ() / 8.0D;
            }
        };
    }
}