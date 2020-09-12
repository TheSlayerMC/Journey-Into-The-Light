package net.journey.dialogue;

import net.journey.dialogue.DialogueBuilder.NodeBuilder;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
    private final String text;
    private final List<Option> options;

    public DialogueNode(String text) {
        this.text = text;
        this.options = new ArrayList<>();
    }

    protected void addOption(Option option) {
        options.add(option);
    }

    public List<Option> getOptions() {
        return options;
    }

    public static class Option {
        private final String text;
        private Action onClickAction = Action.END_DIALOGUE;

        public Option(String text) {
            this.text = text;
        }

        protected void setOnClickAction(Action onClickAction) {
            this.onClickAction = onClickAction;
        }

        public String getText() {
            return text;
        }
    }

    public interface Action {
        Action END_DIALOGUE = (world, player) -> {
            throw new NotImplementedException("");
        };  //FIXME

        static Action newDialogNode(NodeBuilder nodeBuilder) {
            return (world, player) -> {
                throw new NotImplementedException("");
            };
        }

        void onClick(World world, EntityPlayerMP player);
    }
}
