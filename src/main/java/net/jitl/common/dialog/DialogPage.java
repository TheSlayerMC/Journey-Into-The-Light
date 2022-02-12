package net.jitl.common.dialog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class DialogPage {
    private final String phrase;
    @Nullable
    private List<String> options;
    private Consumer<Integer> answerCallback;

    public DialogPage(String phrase) {
        this.phrase = phrase;
    }

    public DialogPage fillWithOptions(@NotNull List<String> options, Consumer<Integer> answerCallback) {
        this.options = options;
        this.answerCallback = answerCallback;
        return this;
    }

    public String getPhrase() {
        return phrase;
    }

    @Nullable
    public List<String> getOptions() {
        return options;
    }

    public boolean hasOptions() {
        return getOptions() != null;
    }

    public boolean hasPhrase() {
        return getPhrase() != null;
    }

    public void consume(int answer) {
        answerCallback.accept(answer);
    }
}
