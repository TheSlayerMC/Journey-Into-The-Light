package ru.timeconqueror.timecore.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.client.render.JsonParsingException;

import javax.vecmath.Vector2f;

public class JsonUtils {
    public static Vector3f getVec3f(String name, JsonElement parent) throws JsonParsingException {
        return toVec3f(get(name, parent.getAsJsonObject()));
    }

    public static Vector3f getVec3f(String name, JsonElement parent, Vector3f defaultVal) throws JsonParsingException {
        JsonElement vecArr = parent.getAsJsonObject().get(name);
        if (vecArr != null) {
            return toVec3f(vecArr);
        } else return defaultVal;
    }

    public static Vector3f toVec3f(JsonElement element) throws JsonParsingException {
        JsonArray arr = verifyArray(element);
        return new Vector3f(arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat());
    }

    public static Vector2f getVec2f(String name, JsonElement parent) throws JsonParsingException {
        JsonArray arr = verifyArray(get(name, parent.getAsJsonObject()));
        return new Vector2f(arr.get(0).getAsFloat(), arr.get(1).getAsFloat());
    }

    public static boolean getBoolean(String name, JsonElement parent) throws JsonParsingException {
        return get(name, parent.getAsJsonObject()).getAsBoolean();
    }

    public static boolean getBoolean(String name, JsonElement parent, boolean defaultVal) throws JsonParsingException {
        JsonElement val = parent.getAsJsonObject().get(name);
        return val != null ? val.getAsBoolean() : defaultVal;
    }

    public static float getFloat(String name, JsonElement parent) throws JsonParsingException {
        return get(name, parent.getAsJsonObject()).getAsFloat();
    }

    public static float getFloat(String name, JsonElement parent, float defaultVal) throws JsonParsingException {
        JsonElement val = parent.getAsJsonObject().get(name);
        return val != null ? val.getAsFloat() : defaultVal;
    }

    public static int getInt(String name, JsonElement parent) throws JsonParsingException {
        return get(name, parent.getAsJsonObject()).getAsInt();
    }

    public static int getInt(String name, JsonElement parent, int defaultVal) throws JsonParsingException {
        JsonElement val = parent.getAsJsonObject().get(name);
        return val != null ? val.getAsInt() : defaultVal;
    }

    public static String getString(String name, JsonElement parent) throws JsonParsingException {
        return get(name, parent.getAsJsonObject()).getAsString();
    }

    public static String getString(String name, JsonElement parent, String defaultVal) throws JsonParsingException {
        JsonElement val = parent.getAsJsonObject().get(name);
        return val != null ? val.getAsString() : defaultVal;
    }

    public static JsonElement get(String name, JsonObject parent) throws JsonParsingException {
        verifyExisting(name, parent);
        return parent.get(name);
    }

    private static void verifyExisting(String name, JsonObject parent) throws JsonParsingException {
        if (!parent.has(name)) throw new JsonParsingException("Can't find property " + name + " in " + parent);
    }

    private static JsonArray verifyArray(JsonElement element) throws JsonParsingException {
        if (!(element instanceof JsonArray)) throw new JsonParsingException("Not a JSON Array: " + element);

        return element.getAsJsonArray();
    }
}
