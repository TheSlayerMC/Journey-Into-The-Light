package net.jitl.common.dialog.engine;

import net.jitl.common.dialog.DialogCharacter;
import net.jitl.common.dialog.api.IDialogAction;

import java.util.List;
import java.util.function.Consumer;

public interface DialogPresenter {

    void presentPhrase(String text, boolean hasOptions, Runnable dialogContinuer);

    void presentOptions(List<String> options, Consumer<Integer> answerCallback);

    void presentCharacter(DialogCharacter character);

    void presentAction(IDialogAction action);

    void onEnd();
}
