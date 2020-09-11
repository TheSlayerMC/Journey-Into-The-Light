package net.journey.common.capability.innercaps;

import net.journey.api.capability.PlayerPortalOverlay;
import net.minecraft.block.Block;

public class PlayerPortalOverlayImpl implements PlayerPortalOverlay {

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

	@Override
	public void onTick() {
		oldPortalOverlayTime = portalOverlayTime;

		float alphaTime = 0.01F;
		if (inPortal) {
			portalOverlayTime += alphaTime;
			inPortal = false;
		} else {
			if (portalOverlayTime > 0) portalOverlayTime -= 0.05F;
			if (portalOverlayTime < 0) portalOverlayTime = 0;
		}
	}

	@Override
	public int timeBeforeTeleport() {
		return timeBeforeTeleport;
	}

	@Override
	public void setInPortal(Block portalBlock) {
		portalBlockToRender = portalBlock;
		inPortal = true;
	}

	public Block getPortalBlockToRender() {
		return portalBlockToRender;
	}

	@Override
	public boolean isInPortal() {
		return inPortal;
	}

	@Override
	public float portalOverlayTime() {
		return portalOverlayTime;
	}

	@Override
	public float oldPortalOverlayTime() {
		return oldPortalOverlayTime;
	}
}
