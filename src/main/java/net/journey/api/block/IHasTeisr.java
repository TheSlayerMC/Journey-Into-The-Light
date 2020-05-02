package net.journey.api.block;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import org.jetbrains.annotations.NotNull;

/**
 * if block that implements this interface has a TileEntityItemStackRenderer, TileEntityItemStackRenderer will be automatically registered.
 */
public interface IHasTeisr {
	@NotNull
	TileEntityItemStackRenderer createTeisr();
}
