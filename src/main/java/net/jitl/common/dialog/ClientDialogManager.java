package net.jitl.common.dialog;

import net.jitl.client.render.gui.dialog.DialogScreen;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.Nullable;

public class ClientDialogManager {
    private static final ClientDialogManager INSTANCE = new ClientDialogManager();

    //TODO clear when leave server on client side
    @Nullable
    private DialogCharacter currentCharacter = null;

    public static ClientDialogManager getInstance() {
        return INSTANCE;
    }

    public void openPage(ClientDialogPage page) {
        Minecraft.getInstance().setScreen(new DialogScreen(page, currentCharacter));
    }

    public void setCharacter(@Nullable DialogCharacter character) {
        currentCharacter = character;
    }

    public void closeDialog() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof DialogScreen) {
            clear();
            mc.setScreen(null);
        }
    }

    private void clear() {
        currentCharacter = null;
    }
}
