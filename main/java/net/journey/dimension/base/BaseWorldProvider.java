package net.journey.dimension.base;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraftforge.client.IRenderHandler;

import javax.annotation.Nullable;

public abstract class BaseWorldProvider extends WorldProvider {
    protected Vec3d fog;
    protected IRenderHandler skyRender;

    public BaseWorldProvider(BiomeProviderSingle provider) {
        this(provider, null);
    }

    protected BaseWorldProvider(BiomeProvider provider, Vec3d fog) {
        this(provider, null, fog);
    }

    protected BaseWorldProvider(BiomeProvider provider, IRenderHandler skyRender, Vec3d fog) {
        this.skyRender = skyRender;
        this.fog = fog;
        this.biomeProvider = provider;
    }


    @Override
    public final BiomeProvider getBiomeProvider() {
        return super.getBiomeProvider();
    }

    @Nullable
    @Override
    public IRenderHandler getSkyRenderer() {
        return skyRender == null
                ? super.getSkyRenderer()
                : skyRender;
    }

    @Override
    public final Vec3d getFogColor(float var1, float var2) {
        return fog == null
                ? super.getFogColor(var1, var2)
                : fog;
    }
}
