package net.jitl.common.dialogue;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CharacterMap {
    private final Map<String, Character> characters = new HashMap<>();

    public record Character(EntityType<?> entityType,
                            @Nullable CompoundTag data) {
    }
}
