package net.journey.api.capability;

import net.minecraft.block.Block;

public interface PlayerPortalOverlay {

	void onTick();

	/**
	 * Universal time before teleporting for all portals
	 */
	int timeBeforeTeleport();

	/**
	 * Call whenever a player collides with portal block
	 */
	void setInPortal(Block portalBlock);

	boolean isInPortal();

	float portalOverlayTime();

	float oldPortalOverlayTime();

	Block getPortalBlockToRender();
}
