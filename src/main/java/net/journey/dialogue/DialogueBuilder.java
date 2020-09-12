package net.journey.dialogue;

public class DialogueBuilder {
    public static DialogueNode createDialog(NodeBuilder rootBuilder) {
        return rootBuilder.node;
    }

    public static class NodeBuilder {
        private final DialogueNode node;

        private NodeBuilder(String text) {
            node = new DialogueNode(text);
        }

        public static NodeBuilder create(String text) {
            return new NodeBuilder(text);
        }

        public NodeBuilder addOption(OptionBuilder builder) {
            node.addOption(builder.option);
            return this;
        }
    }

    public static class OptionBuilder {
        private final DialogueNode.Option option;

        private OptionBuilder(String text) {
            option = new DialogueNode.Option(text);
        }

        public static OptionBuilder create(String text) {
            return new OptionBuilder(text);
        }

        public OptionBuilder leadsTo(DialogueNode.Action action) {
            option.setOnClickAction(action);
            return this;
        }
    }
}
