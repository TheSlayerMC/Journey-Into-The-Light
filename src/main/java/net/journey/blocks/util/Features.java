package net.journey.blocks.util;

import net.journey.api.block.FeatureProvider;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class Features {

	@Nullable
	private Supplier<TileEntityItemStackRenderer> teisr = null;

	@Nullable
	private ResourceLocation itemModelLocation = null;

	private DummyStateData dummyVariantBlockState = null;

	private Features() {
	}

	public @Nullable ResourceLocation getItemModelLocation() {
		return itemModelLocation;
	}

	public @Nullable Supplier<TileEntityItemStackRenderer> getTeisr() {
		return teisr;
	}

	@Nullable
	public DummyStateData getDummyVariantBlockStateData() {
		return dummyVariantBlockState;
	}

	public static class Builder {
		private final Features feature = new Features();

		public static Builder create() {
			return new Builder();
		}

		/**
		 * Is needed for TESR-only blocks, where we need to setup breaking particles.
		 * <p>
		 * Example of using:
		 * <blockquote>
		 * <pre>
		 *  {
		 *      "forge_marker": 1,
		 *      "defaults": {
		 *          "textures": {
		 *              "particle": "blocks/dirt"
		 *          },
		 *          "model": "block"
		 *      },
		 *      "variants": {
		 *          "dummy": [{}]
		 *      }
		 *  }
		 *     </pre>
		 * </blockquote>
		 *
		 * @param overriddenStateLocation location, where you will place state json file.
		 *                                If provided null, then state json file location will be default.
		 */
		public Builder enableDummyVariantBlockState(@Nullable ResourceLocation overriddenStateLocation) {
			feature.dummyVariantBlockState = new DummyStateData(overriddenStateLocation);

			return this;
		}

		/**
		 * Is needed for TESR-only blocks, where we need to setup breaking particles.
		 * <p>
		 * Example of using:
		 * <blockquote>
		 * <pre>
		 *  {
		 *      "forge_marker": 1,
		 *      "defaults": {
		 *          "textures": {
		 *              "particle": "blocks/dirt"
		 *          },
		 *          "model": "block"
		 *      },
		 *      "variants": {
		 *          "dummy": [{}]
		 *      }
		 *  }
		 *     </pre>
		 * </blockquote>
		 */
		public Builder enableDummyVariantBlockState() {
			return enableDummyVariantBlockState(null);
		}

		/**
		 * If block that implements interface {@link FeatureProvider} has a TileEntityItemStackRenderer, TileEntityItemStackRenderer will be automatically registered.
		 */
		public Builder regTEISR(Supplier<TileEntityItemStackRenderer> teisr) {
			feature.teisr = teisr;
			return this;
		}

		public Builder setCustomItemModelLocation(ResourceLocation location) {
			feature.itemModelLocation = location;
			return this;
		}

		public Features build() {
			return feature;
		}
	}

	public static class DummyStateData {
		@Nullable
		private final ResourceLocation overriddenStateLocation;

		public DummyStateData(@Nullable ResourceLocation overriddenStateLocation) {
			this.overriddenStateLocation = overriddenStateLocation;
		}

		public @Nullable ResourceLocation getOverriddenStateLocation() {
			return overriddenStateLocation;
		}
	}
}
