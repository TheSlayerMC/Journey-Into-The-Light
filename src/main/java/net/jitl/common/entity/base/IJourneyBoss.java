package net.jitl.common.entity.base;

import net.jitl.common.helper.JMusic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IJourneyBoss {
    ResourceLocation getBarTexture();

    default void renderBossBar(RenderGameOverlayEvent event) {
        //TODO: boss bar rendering
    }

    JMusic getBossMusic();
}
