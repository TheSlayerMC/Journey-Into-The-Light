package ru.timeconqueror.timecore.client.render.animation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.api.util.CollectionUtils;
import ru.timeconqueror.timecore.client.render.JsonParsingException;
import ru.timeconqueror.timecore.util.JsonUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class JsonAnimationParser {
    private static final String[] ACCEPTABLE_FORMAT_VERSIONS = new String[]{"1.8.0"};
    private static final Gson GSON = (new GsonBuilder()).create();

    public List<IAnimation> parseAnimations(@NotNull ResourceLocation fileLocation) throws JsonParsingException {
        try (final IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(fileLocation)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            JsonObject json = net.minecraft.util.JsonUtils.gsonDeserialize(GSON, reader, JsonObject.class, true);
            return parseAnimation(json);

        } catch (Throwable e) {
            throw new JsonParsingException(e);
        }
    }

    @NotNull
    private List<IAnimation> parseAnimation(JsonObject object) throws JsonParsingException {
        if (object.has("format_version")) {
            String formatVersion = object.get("format_version").getAsString();
            checkFormatVersion(formatVersion);
        }

        JsonObject animations = JsonUtils.get("animations", object).getAsJsonObject();
        List<IAnimation> animationList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> animationEntry : animations.entrySet()) {
            String name = animationEntry.getKey();
            JsonElement animationJson = animationEntry.getValue();

            boolean loop = JsonUtils.getBoolean("loop", animationJson, false);
            int animationLength = (int) (JsonUtils.getFloat("animation_length", animationJson) * 1000);

            List<BoneOption> boneOptions = new ArrayList<>();
            JsonElement bones = animationJson.getAsJsonObject().get("bones");
            if (bones != null) {
                for (Map.Entry<String, JsonElement> boneEntryJson : JsonUtils.get("bones", animationJson.getAsJsonObject()).getAsJsonObject().entrySet()) {
                    String boneName = boneEntryJson.getKey();
                    JsonObject boneJson = boneEntryJson.getValue().getAsJsonObject();
                    BoneOption option = parseAnimationBone(boneName, boneJson);
                    boneOptions.add(option);
                }
            }

            animationList.add(new Animation(loop, name, animationLength, !boneOptions.isEmpty() ? Collections.unmodifiableMap(boneOptions.stream().collect(Collectors.toMap(BoneOption::getName, boneOption -> boneOption))) : null));
        }

        return animationList;
    }

    private BoneOption parseAnimationBone(String boneName, JsonObject boneJson) throws JsonParsingException {
        List<KeyFrame> rotationFrames = parseKeyFrameArr("rotation", boneJson);
        List<KeyFrame> positionFrames = parseKeyFrameArr("position", boneJson);
        List<KeyFrame> scaleFrames = parseKeyFrameArr("scale", boneJson);

        return new BoneOption(boneName, rotationFrames, positionFrames, scaleFrames);
    }

    @Nullable
    private List<KeyFrame> parseKeyFrameArr(String optionName, JsonObject boneJson) throws JsonParsingException {
        List<KeyFrame> keyFrames = new ArrayList<>();

        if (boneJson.has(optionName)) {
            JsonElement frameContainerJson = boneJson.get(optionName);
            if (frameContainerJson.isJsonArray()) {
                Vector3f vec = JsonUtils.toVec3f(frameContainerJson);
                keyFrames.add(new KeyFrame(0, vec));

            } else if (frameContainerJson.isJsonObject()) {
                float prevTime = -1;
                for (Map.Entry<String, JsonElement> keyEntry : frameContainerJson.getAsJsonObject().entrySet()) {
                    float time = Float.parseFloat(keyEntry.getKey());

                    if (prevTime == -1) {
                        prevTime = time;
                    } else if (time <= prevTime) {
                        throw new JsonParsingException("Keyframe with name " + keyEntry.getKey() + " (" + keyEntry.getValue() + ") should have time marker that is bigger than previous. Provided: " + time + ", previous: " + prevTime);
                    }

                    Vector3f vec = JsonUtils.toVec3f(keyEntry.getValue());

                    keyFrames.add(new KeyFrame((int) (time * 1000), vec));
                }
            }
        }

        return !keyFrames.isEmpty() ? Collections.unmodifiableList(keyFrames) : null;
    }

    private void checkFormatVersion(String version) throws JsonParsingException {
        if (!CollectionUtils.contains(ACCEPTABLE_FORMAT_VERSIONS, version)) {
            throw new JsonParsingException("The format version " + version + " is not supported. Supported versions: " + Arrays.toString(ACCEPTABLE_FORMAT_VERSIONS));
        }
    }
}