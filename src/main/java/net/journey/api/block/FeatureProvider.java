package net.journey.api.block;

import net.journey.blocks.util.Features;
import org.jetbrains.annotations.NotNull;

public interface FeatureProvider {
	@NotNull
	Features getExtraFeatures();
}
