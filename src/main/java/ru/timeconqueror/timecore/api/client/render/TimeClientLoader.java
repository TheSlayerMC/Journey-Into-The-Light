package ru.timeconqueror.timecore.api.client.render;

import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.client.render.animation.Animation;
import ru.timeconqueror.timecore.client.render.animation.JsonAnimationParser;
import ru.timeconqueror.timecore.client.render.model.JsonModelParser;

import java.util.List;

public class TimeClientLoader {
    private static List<TimeModel> BROKEN_MODEL = loadJsonModels(new ResourceLocation(TimeCore.MODID, "models/broken.json"));

    public static List<TimeModel> loadJsonModels(ResourceLocation location) {
        try {
            return new JsonModelParser().parseJsonModel(location);
        } catch (Throwable e) {
            TimeCore.logHelper.error("Can't load model " + location.toString(), e);
        }

        return BROKEN_MODEL;
    }

    public static TimeModel loadJsonModel(ResourceLocation location) {
        List<TimeModel> timeModels = loadJsonModels(location);
        if (timeModels.size() != 1) {
            TimeCore.logHelper.error("Can't load model " + location.toString() + " due to the file contains more than one model. Use TimeModelLoader#loadJsonModels method instead.");
            return BROKEN_MODEL.get(0);
        }

        return timeModels.get(0);
    }

    public static TimeEntityModel loadJsonEntityModel(ResourceLocation location) {
        return new TimeEntityModel(loadJsonModel(location));
    }

    public static List<Animation> loadAnimation(ResourceLocation location) {
        try {
            return new JsonAnimationParser().parseAnimations(location);
        } catch (Throwable e) {
            TimeCore.logHelper.error("Can't load animation " + location.toString(), e);
        }

        return null;
    }
}
