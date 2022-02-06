package net.jitl.common.dialogue;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialoguePage {
    public static final DialoguePage END = new DialoguePage("", null, "", new CharacterMap());

    /**
     * Will be shown when there is no option added to the node.
     */
    private static final List<Option> STANDBY_END_OPTION_LIST = Collections.singletonList(new Option("dialogue.jitl.standby_end_option"));

    private final String text;
    @Nullable
    private List<Option> options;
    private final String characterId;
    private final CharacterMap characterMap;

    public DialoguePage(String text, @Nullable List<Option> options, String characterId, CharacterMap characterMap) {
        this.text = text;
        this.options = options;
        this.characterId = characterId;
        this.characterMap = characterMap;
    }

    @Deprecated //TODO remove
    protected void addOption(Option option) {
        if (options == null) options = new ArrayList<>();
        options.add(option);
    }

    public List<Option> getOptions() {
        return options != null ? options : STANDBY_END_OPTION_LIST;
    }

    public String getTextKey() {
        return text;
    }

    @Override
    public String toString() {
        return "DialogueNode{" +
                "text='" + text + '\'' +
                ", options=" + options +
                '}';
    }

    public static class Option {
        private final String text;
        private DialoguePage nextNode = END;
        private Action onClickAction = Action.EMPTY;

        protected Option(String text) {
            this.text = text;
        }

        protected void setOnClickAction(Action onClickAction) {
            this.onClickAction = onClickAction;
        }

        protected void setNextNode(DialoguePage nextNode) {
            this.nextNode = nextNode;
        }

        public String getText() {
            return text;
        }

        public DialoguePage getNextNode() {
            return nextNode;
        }

        public void onClickAction(Level world, ServerPlayer player) {
            onClickAction.onClick(world, player);
        }

        @Override
        public String toString() {
            return "Option{" +
                    "text='" + text + '\'' +
                    ", hasNextNode=" + (nextNode != END) +
                    '}';
        }
    }

    public interface Action {
        Action EMPTY = (world, player) -> {
        };

        void onClick(Level world, ServerPlayer player);
    }
}