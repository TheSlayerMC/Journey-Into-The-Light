package net.jitl.client.isometric.capturetask;

import net.jitl.client.isometric.rendertarget.RenderTargetCapturer;
import net.jitl.client.isometric.rendertarget.RenderTargetWriter;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;

import java.nio.file.Path;

public class CaptureTask implements RenderTickTask {

    private static final Minecraft MC = Minecraft.getInstance();

    private final Path file;

    private int frame;
    private int displayWidth;
    private int displayHeight;

    public CaptureTask(Path file) {
        this.file = file;
    }

    @Override
    public boolean onRenderTick(TickEvent.RenderTickEvent evt) throws Exception {
        JClientConfig.GuiCategory guiCategory = JConfigs.CLIENT.guiCategory;
        switch (frame) {
            // override viewport size (the following frame will be black)
            case 0:
                displayWidth = MC.getWindow().getWidth();
                displayHeight = MC.getWindow().getHeight();

                int width = guiCategory.getBigScreenshotWidth();
                int height = guiCategory.getBigScreenshotHeight();

                // resize viewport/framebuffer
                MC.getWindow().onFramebufferResize(MC.getWindow().getWindow(), width, height);
                break;

            // capture screenshot and restore viewport size
            case 3:
                try {
                    RenderTargetCapturer fbc = new RenderTargetCapturer();
                    RenderTargetWriter fbw = new RenderTargetWriter(file, fbc);
                    fbw.write();
                } finally {
                    // restore viewport/framebuffer
                    MC.getWindow().onFramebufferResize(MC.getWindow().getWindow(), displayWidth, displayHeight);
                }
                break;
        }

        frame++;
        return frame > 3;
    }
}