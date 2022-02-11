package net.jitl.client.isometric;

import com.mojang.blaze3d.platform.InputConstants;
import net.jitl.client.isometric.capturetask.CaptureTask;
import net.jitl.client.isometric.capturetask.RenderTickTask;
import net.jitl.core.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.jitl.client.eventhandler.KeybindEventHandler.keyBigScreenshot;
import static net.minecraft.network.chat.ClickEvent.Action.OPEN_FILE;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class BigScreenshotHandler {
    private static final Minecraft MC = Minecraft.getInstance();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

    private static Path taskFile;
    private static RenderTickTask task;

    public static void handleBigScreenshotKeys(InputConstants.Key key) {
        if (task != null) {
            return;
        }

        if (key == keyBigScreenshot.getKey()) {
            taskFile = getScreenshotFile();
            task = new CaptureTask(taskFile);
        }
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent evt) {
        if (task == null) {
            return;
        }

        try {
            if (task.onRenderTick(evt)) {
                task = null;
                printFileLink("Big Screenshot Taken!", taskFile.toFile());
            }
        } catch (Exception exception) {
            JITL.LOGGER.error("Screenshot Capture Failed", exception);

            task = null;
        }
    }

    private static Path getScreenshotFile() {
        Path dir = MC.gameDirectory.toPath().resolve("screenshots");

        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        // loop though suffixes while the file exists
        int i = 0;
        Path file;
        do {
            file = dir.resolve(String.format("huge_%s_%04d.tga", DATE_FORMAT.format(new Date()), i++));
        } while (Files.exists(file));

        JITL.LOGGER.info(file);

        return file;
    }


    public static void print(String msg, Color format, Object... args) {
        if (MC.screen == null) {
            return;
        }

        ChatComponent chat = MC.gui.getChat();
        TranslatableComponent ret = new TranslatableComponent(msg, args);
        ret.getStyle().withColor(format.getRGB());

        chat.addMessage(ret);
    }

    public static void print(String msg, Object... args) {
        print(msg, null, args);
    }

    public static void printFileLink(String msg, File file) {
        TranslatableComponent text = new TranslatableComponent(file.getName());
        String path;

        try {
            path = file.getAbsoluteFile().getCanonicalPath();
        } catch (IOException ex) {
            path = file.getAbsolutePath();
        }

        text.getStyle().withClickEvent(new ClickEvent(OPEN_FILE, path));
        text.getStyle().setUnderlined(true);

        print(msg, text);
    }
}
