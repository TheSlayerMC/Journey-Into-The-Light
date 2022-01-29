package net.jitl.common.capability.player.data;

import net.jitl.core.JITL;
import net.minecraft.world.level.block.Block;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class Portal extends PropertyContainer {
    public float portalOverlayTime;
    public float oldPortalOverlayTime;

    public Block portalBlockToRender;

    /**
     * The time it takes for a player to teleport after colliding with a JITL portal block
     */
    public int timeBeforeTeleport = 300;

    /**
     * Called whenever a player has collided with a JITL portal block. initiates portal animation
     */
    public boolean inPortal = false;

    public void onTick() {
        oldPortalOverlayTime = portalOverlayTime;

        float alphaTime = 0.01F;
        if (inPortal) {
            portalOverlayTime += alphaTime;
            JITL.LOGGER.info("Portal Overlay Time" + portalOverlayTime);
            JITL.LOGGER.info("Alpha Time" + alphaTime);
            inPortal = false;
        } else {
            if (portalOverlayTime > 0) portalOverlayTime -= 0.05F;
            if (portalOverlayTime < 0) portalOverlayTime = 0;
        }
    }

    public int getTimeBeforeTeleport() {
        return timeBeforeTeleport;
    }

    public void setPortalTime(int time) {
        this.timeBeforeTeleport = time;
    }

    public void setInPortal(Block portalBlock) {
        portalBlockToRender = portalBlock;
        inPortal = true;
    }

    public Block getPortalBlockToRender() {
        return portalBlockToRender;
    }

    public boolean isInPortal() {
        return inPortal;
    }

    public float getPortalOverlayTime() {
        return portalOverlayTime;
    }

    public float getOldPortalOverlayTime() {
        return oldPortalOverlayTime;
    }
}