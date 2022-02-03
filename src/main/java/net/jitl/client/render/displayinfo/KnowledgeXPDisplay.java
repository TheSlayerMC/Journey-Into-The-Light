package net.jitl.client.render.displayinfo;

import net.jitl.client.render.overlay.internal.JDisplayInfo;
import net.jitl.client.render.overlay.internal.JFrameType;
import net.minecraft.network.chat.TranslatableComponent;

public class KnowledgeXPDisplay extends JDisplayInfo {

    public KnowledgeXPDisplay(String name, boolean isLevel) {
        super(new TranslatableComponent("jitl.knowledge." + name.toLowerCase()), isLevel ? JFrameType.LEVEL : JFrameType.XP);
    }
}
