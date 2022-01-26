package net.jitl.client.render.overlay;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public enum JFrameType {

    LEVEL("Level Up!", 26),
    XP("XP Gained!", 52);

    private final String name;
    private final int texture;
    private final Component displayName;

    JFrameType(String name, int texture) {
        this.name = name;
        this.texture = texture;
        this.displayName = new TranslatableComponent(name);
    }

    public String getName() {
        return this.name;
    }

    public int getTexture() {
        return this.texture;
    }

    public Component getDisplayName() {
        return this.displayName;
    }
}