package net.jitl.common.capability.player.data;

import net.jitl.core.util.ClientTools;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class Portal extends PropertyContainer {
    //FIXME sync
    public float portalOverlayTime = 0F;
    public float oldPortalOverlayTime = 0F;
    public Block portalBlockToRender;

    /**
     * The time it takes for a player to teleport after colliding with a JITL portal block
     */
    public int portalTimer = 0;

    /**
     * Called whenever a player has collided with a JITL portal block. initiates portal animation
     */
    public boolean inPortal = false;

    public void onTick() {
        oldPortalOverlayTime = portalOverlayTime;
        float alphaTime = 0.01F;
        if (inPortal) {
            ++portalTimer;
            portalOverlayTime += alphaTime;
            if (portalOverlayTime > 1.0F) portalOverlayTime = 1.0F;
            if (portalOverlayTime == 0.01F) {
                ClientTools.playLocalSound(SoundEvents.PORTAL_TRIGGER, 1.0F, 0.65F);
            }
            inPortal = false;
        } else {
            if (portalOverlayTime > 0) portalOverlayTime -= 0.05F;

            if (portalOverlayTime < 0) portalOverlayTime = 0;

            if (portalTimer > 0) portalTimer -= 4;
        }
    }

    public void setInPortal(Block portalBlock, boolean inPortal) {
        portalBlockToRender = portalBlock;
        this.inPortal = inPortal;
    }

    public Block getPortalBlockToRender() {
        return portalBlockToRender;
    }

    public void setPortalTimer(int timer) {
        portalTimer = timer;
    }

    public int getPortalTimer() {
        return portalTimer;
    }

    public float getPortalOverlayTime() {
        return portalOverlayTime;
    }

    public float getOldPortalOverlayTime() {
        return oldPortalOverlayTime;
    }
}