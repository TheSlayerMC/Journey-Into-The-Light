package ru.timeconqueror.timecore.animation.util;

import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.Layer;
import ru.timeconqueror.timecore.animation.ServerAnimationManager;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.animation.watcher.TransitionWatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnimationSerializer {
    private static final WatcherSerializer<AnimationWatcher> COMMON_SERIALIZER = new AnimationWatcher.Serializer();
    private static final WatcherSerializer<TransitionWatcher> TRANSITION_SERIALIZER = new TransitionWatcher.Serializer();

    public static void serializeWatchers(ServerAnimationManager<?> animationManager, PacketBuffer buffer) {
        Set<String> layerNames = animationManager.getLayerNames();

        buffer.writeInt(layerNames.size());

        for (String name : layerNames) {
            Layer layer = animationManager.getLayer(name);
            AnimationWatcher animationWatcher = layer.getAnimationWatcher();

            boolean hasWatcher = animationWatcher != null;
            buffer.writeBoolean(hasWatcher);

            if (hasWatcher) {
                buffer.writeString(name);
                serializeWatcher(animationWatcher, buffer);
            }
        }
    }

    public static Map<String, AnimationWatcher> deserializeWatchers(PacketBuffer buffer) {
        int layerCount = buffer.readInt();

        Map<String, AnimationWatcher> layerMap = new HashMap<>();

        for (int i = 0; i < layerCount; i++) {
            boolean hasWatcher = buffer.readBoolean();
            if (hasWatcher) {
                String layerName = buffer.readString(Short.MAX_VALUE);
                AnimationWatcher watcher = deserializeWatcher(buffer);
                layerMap.put(layerName, watcher);
            }
        }

        return layerMap;
    }

    private static void serializeWatcher(@NotNull AnimationWatcher watcher, PacketBuffer buffer) {
        buffer.writeBoolean(watcher instanceof TransitionWatcher);

        if (watcher instanceof TransitionWatcher) {
            TRANSITION_SERIALIZER.serialize(((TransitionWatcher) watcher), buffer);
        } else {
            COMMON_SERIALIZER.serialize(watcher, buffer);
        }
    }

    private static AnimationWatcher deserializeWatcher(PacketBuffer buffer) {
        boolean isTransitionWatcher = buffer.readBoolean();
        if (isTransitionWatcher) {
            return TRANSITION_SERIALIZER.deserialize(buffer);
        } else {
            return COMMON_SERIALIZER.deserialize(buffer);
        }
    }
}
