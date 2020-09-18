package net.journey.dimension.base;

import net.journey.util.gui.RenderUtils;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class BaseWorldProvider extends WorldProvider {
	protected Vec3d customFog;
	protected IRenderHandler customSkyRender;
	protected Function<World, BiomeProvider> biomeProviderCreator;

	public BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator) {
		this(biomeProviderCreator, null);
	}

	protected BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator, @Deprecated Vec3d customFog) {
		this(biomeProviderCreator, null, customFog);
	}

	protected BaseWorldProvider(Function<World, BiomeProvider> biomeProviderCreator, IRenderHandler customSkyRender, @Deprecated Vec3d customFog) {
		this.customSkyRender = customSkyRender;
		this.customFog = customFog;
		this.biomeProviderCreator = biomeProviderCreator;
	}

	/**
	 * New version of customFog param in constructor.
	 */
	protected void setCustomFogColor(int argb) {
		customFog = new Vec3d(RenderUtils.getRed(argb) / 255F, RenderUtils.getGreen(argb) / 255F, RenderUtils.getBlue(argb) / 255F);
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
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return customFog == null
				? super.getFogColor(celestialAngle, partialTicks)
				: customFog;
	}
}
