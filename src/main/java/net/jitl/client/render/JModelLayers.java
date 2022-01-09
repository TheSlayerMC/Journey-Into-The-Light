package net.jitl.client.render;

import com.google.common.collect.Sets;
import net.jitl.JITL;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class JModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation CAPYBARA_MODEL_LAYER = register("capbara");
    public static final ModelLayerLocation FROZEN_GUARDIAN_MODEL_LAYER = register("frozen_guardian");
    public static final ModelLayerLocation HONGO_MODEL_LAYER = register("hongo");
    public static final ModelLayerLocation WITHERSHROOM_MODEL_LAYER = register("withershroom");
    public static final ModelLayerLocation HONGLOW_MODEL_LAYER = register("honglow");
    public static final ModelLayerLocation FROZEN_TROLL_MODEL_LAYER = register("frozen_troll");
    public static final ModelLayerLocation SHATTERER_MODEL_LAYER = register("shatterer");
    public static final ModelLayerLocation PHANTASM_MODEL_LAYER = register("phantasm");
    public static final ModelLayerLocation MAGE_MODEL_LAYER = register("mage");
    public static final ModelLayerLocation ESKIMO_MODEL_LAYER = register("eskimo");
    public static final ModelLayerLocation MINI_BOOM_LAYER = register("mini_boom");
    public static final ModelLayerLocation MINI_BOOM_CHARGED_LAYER = register("mini_boom", "armor");
    public static final ModelLayerLocation SHIVERING_RAM_LAYER = register("shivering_ram");
    public static final ModelLayerLocation SHIVERING_RAM_WOOL_LAYER = register("mini_boom", "wool");

    public static final ModelLayerLocation OBELISK_MODEL_LAYER = register("obelisk");

    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(JITL.rl(path), model);
    }
}