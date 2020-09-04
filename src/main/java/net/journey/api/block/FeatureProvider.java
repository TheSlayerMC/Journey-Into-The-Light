package net.journey.api.block;

import net.journey.blocks.util.Feature;
import org.jetbrains.annotations.NotNull;

public interface FeatureProvider {
	@NotNull
	Feature getExtraFeatures();
}
