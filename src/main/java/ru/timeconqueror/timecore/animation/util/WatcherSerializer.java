package ru.timeconqueror.timecore.animation.util;

import net.minecraft.network.PacketBuffer;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;

public interface WatcherSerializer<T extends AnimationWatcher> {
    void serialize(T watcher, PacketBuffer buffer);

    T deserialize(PacketBuffer buffer);
}
