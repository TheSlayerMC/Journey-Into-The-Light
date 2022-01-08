package net.jitl.client.render;

import com.google.common.collect.Sets;
import net.jitl.JITL;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class JModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation CAPYBARA_MODEL_LAYER = register("capbara");

    private static ModelLayerLocation register(String path_) {
        return register(path_, "main");
    }

    private static ModelLayerLocation register(String path_, String model_) {
        ModelLayerLocation modellayerlocation = createLocation(path_, model_);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path_, String model_) {
        return new ModelLayerLocation(JITL.rl(path_), model_);
    }
}
