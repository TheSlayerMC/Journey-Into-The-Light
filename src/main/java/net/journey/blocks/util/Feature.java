package net.journey.blocks.util;

import net.journey.api.block.FeatureProvider;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class Feature {
	@Nullable
	private Supplier<TileEntityItemStackRenderer> teisr = null;
	@Nullable
	private ResourceLocation itemModelLocation = null;
	private boolean regDummyVariantBlockState = false;

	private Feature() {
	}

	public @Nullable ResourceLocation getItemModelLocation() {
		return itemModelLocation;
	}

	public @Nullable Supplier<TileEntityItemStackRenderer> getTeisr() {
		return teisr;
	}

	public boolean isRegDummyVariantBlockState() {
		return regDummyVariantBlockState;
	}

	public static class Builder {
		private final Feature feature = new Feature();

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
		 */
		public Builder regDummyVariantBlockState() {
			feature.regDummyVariantBlockState = true;

			return this;
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

		public Feature build() {
			return feature;
		}
	}
}
