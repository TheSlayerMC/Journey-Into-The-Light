package net.jitl.common.dialog;

import kotlin.NotImplementedError;
import net.jitl.common.dialog.api.IDialogAction;
import net.jitl.common.dialog.engine.DialogPresenter;
import net.jitl.common.dialog.engine.DialogProcessor;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.dialogue.SOpenDialogGuiPacket;
import net.jitl.core.network.dialogue.SSetCharacterPacket;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

//TODO save and self-destroy if player quit
public class DialogTracker {
    private final String DEFAULT_TEXT = "...";

    private final ServerPlayer player;
    private final Dialog dialog;
    private final DialogProcessor processor;

    @Nullable
    private DialogPage currentPage;

    public DialogTracker(ServerPlayer player, Dialog dialog) {
        this.dialog = dialog;
        this.player = player;
        this.processor = new DialogProcessor(dialog, new InGuiPresenter());
    }

    public void start() {
        processor.start();
    }

    private void saveAndDestroy() {
        throw new NotImplementedError();
    }

    public void save() {
        throw new NotImplementedError();
    }

    public boolean handleOptionClick(int answer) {
        // this can be achieved when someone try to use cheaty exploits
        if (currentPage == null || currentPage.getOptions() == null || answer < 0 || answer >= currentPage.getOptions().size()) {
            return false;
        }

        currentPage.consume(answer);
        return true;
    }

    public Dialog getDialog() {
        return dialog;
    }

    private ServerPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DialogTracker that)) return false;

        return dialog.equals(that.dialog);
    }

    @Override
    public int hashCode() {
        return dialog.hashCode();
    }

    private class InGuiPresenter implements DialogPresenter {
        @Override
        public void presentPhrase(String text, boolean hasOptions, Runnable dialogContinuer) {
            currentPage = new DialogPage(text);
            if (hasOptions) {
                dialogContinuer.run();
                return;
            }

            currentPage.fillWithOptions(Collections.singletonList(DEFAULT_TEXT), integer -> dialogContinuer.run());
            openPage(currentPage);
        }

        @Override
        public void presentOptions(List<String> options, Consumer<Integer> answerCallback) {
            if (currentPage == null) {
                currentPage = new DialogPage(DEFAULT_TEXT);
            }

            currentPage.fillWithOptions(options, answerCallback);

            openPage(currentPage);
        }

        private void openPage(DialogPage page) {
            JPacketHandler.sendToPlayer(player, new SOpenDialogGuiPacket(page));
        }

        @Override
        public void presentCharacter(DialogCharacter character) {
            JPacketHandler.sendToPlayer(player, new SSetCharacterPacket(character));
        }

        @Override
        public void presentAction(IDialogAction action) {
            action.onClick(player.level, player);
        }

        @Override
        public void onEnd() {
            saveAndDestroy();// FIXME really?
        }
    }
}