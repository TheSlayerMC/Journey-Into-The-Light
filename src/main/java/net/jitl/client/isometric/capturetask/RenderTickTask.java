package net.jitl.client.isometric.capturetask;

import net.minecraftforge.event.TickEvent;

public interface RenderTickTask {

    /**
     * Called on every frame to update the task.
     *
     * @param evt Current render tick event
     * @return true if the task is done and can be disposed or false if it should continue to be updated.
     */
    boolean onRenderTick(TickEvent.RenderTickEvent evt) throws Exception;
}
