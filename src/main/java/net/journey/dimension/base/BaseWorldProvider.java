package net.journey.dimension.base;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.IRenderHandler;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class BaseWorldProvider extends WorldProvider {
    protected Vec3d customFog;
    protected IRenderHandler customSkyRender;
    protected Function<World, BiomeProvider> biomeProviderCreator;

    public BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator) {
        this(biomeProviderCreator, null);
    }

    protected BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator, Vec3d customFog) {
        this(biomeProviderCreator, null, customFog);
    }

    protected BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator, IRenderHandler customSkyRender, Vec3d customFog) {
        this.customSkyRender = customSkyRender;
        this.customFog = customFog;
        this.biomeProviderCreator = biomeProviderCreator;
    }

    @Override
    protected void init() {
        hasSkyLight = true;

        biomeProvider = biomeProviderCreator.apply(world);
    }

    @Nullable
    @Override
    public IRenderHandler getSkyRenderer() {
        return customSkyRender == null
                ? super.getSkyRenderer()
                : customSkyRender;
    }

    @Override
    public final Vec3d getFogColor(float var1, float var2) {
        return customFog == null
                ? super.getFogColor(var1, var2)
                : customFog;
    }
}
