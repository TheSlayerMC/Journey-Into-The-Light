package net.jitl.client.render.overlay.internal;

import net.minecraft.network.chat.Component;

public class JDisplayInfo {

    private final Component description;
    private final JFrameType frame;

    public JDisplayInfo(Component description, JFrameType frame) {
        this.description = description;
        this.frame = frame;
    }

    public Component getDescription() {
        return this.description;
    }

    public JFrameType getFrame() {
        return this.frame;
    }

}