package net.jitl.client.render;

import com.google.common.collect.Sets;
import net.jitl.common.entity.vehicle.JBoat;
import net.jitl.core.JITL;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class JModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation CAPYBARA_MODEL_LAYER = register("capbara");
    public static final ModelLayerLocation CAPYBARA_SADDLE_LAYER = register("capbara", "saddle");
    public static final ModelLayerLocation FROZEN_GUARDIAN_MODEL_LAYER = register("frozen_guardian");
    public static final ModelLayerLocation HONGO_MODEL_LAYER = register("hongo");
    public static final ModelLayerLocation BROWN_HONGO_MODEL_LAYER = register("brown_hongo");
    public static final ModelLayerLocation WITHERSHROOM_MODEL_LAYER = register("withershroom");
    public static final ModelLayerLocation HONGLOW_MODEL_LAYER = register("honglow");
    public static final ModelLayerLocation FROZEN_TROLL_MODEL_LAYER = register("frozen_troll");
    public static final ModelLayerLocation FROZEN_TROLL_HELD_ITEM_LAYER = register("frozen_troll", "held_item");
    public static final ModelLayerLocation SHATTERER_MODEL_LAYER = register("shatterer");
    public static final ModelLayerLocation PHANTASM_MODEL_LAYER = register("phantasm");
    public static final ModelLayerLocation MAGE_MODEL_LAYER = register("mage");
    public static final ModelLayerLocation ESKIMO_MODEL_LAYER = register("eskimo");
    public static final ModelLayerLocation MINI_BOOM_LAYER = register("mini_boom");
    public static final ModelLayerLocation EUCA_HOPPER_MODEL_LAYER = register("euca_hopper");
    public static final ModelLayerLocation EUCA_CHARGER_MODEL_LAYER = register("euca_charger");
    public static final ModelLayerLocation GOLD_BOT_MODEL_LAYER = register("gold_bot");
    public static final ModelLayerLocation GOLDER_MODEL_LAYER = register("golder");
    public static final ModelLayerLocation DYNASTER_MODEL_LAYER = register("dynaster");
    public static final ModelLayerLocation SHIMMERER_MODEL_LAYER = register("shimmerer");
    public static final ModelLayerLocation MINI_BOOM_CHARGED_LAYER = register("mini_boom", "armor");
    public static final ModelLayerLocation SHIVERING_RAM_LAYER = register("shivering_ram");
    public static final ModelLayerLocation SHIVERING_RAM_WOOL_LAYER = register("shivering_ram", "wool");
    public static final ModelLayerLocation OBELISK_TOP_MODEL_LAYER = register("obelisk_top");
    public static final ModelLayerLocation OBELISK_BOTTOM_MODEL_LAYER = register("obelisk_bottom");
    public static final ModelLayerLocation MINI_GHAST_LAYER = register("mini_ghast");

    public static final ModelLayerLocation JCHEST = register("jchest");
    public static final ModelLayerLocation JDOUBLE_CHEST_LEFT = register("jdouble_chest_left");
    public static final ModelLayerLocation JDOUBLE_CHEST_RIGHT = register("jdouble_chest_right");


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

    public static ModelLayerLocation createBoatModelName(JBoat.Type type) {
        return createLocation("boat/" + type.getName(), "main");
    }
}