package net.jitl.common.dialogue;

import net.minecraft.resources.ResourceLocation;

public class DialogueBuilder {
    public static DialogueNode branch(NodeBuilder rootBuilder) {
        return rootBuilder.node;
    }

    public static Dialogue dialogue(ResourceLocation id, NodeBuilder rootNodeBuilder) {
        return dialogue(id, rootNodeBuilder.node);
    }

    public static Dialogue dialogue(ResourceLocation id, DialogueNode rootNode) {
        return new Dialogue(id, rootNode);
    }

    public static NodeBuilder node(String text) {
        return new NodeBuilder(text);
    }

    public static OptionBuilder option(String text) {
        return new OptionBuilder("> " + text);
    }

    public static class NodeBuilder {
        private final DialogueNode node;

        private NodeBuilder(String text) {
            node = new DialogueNode(text);
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

        public OptionBuilder leadsTo(NodeBuilder nextNode) {
            option.setNextNode(nextNode.node);
            return this;
        }

        public OptionBuilder withAction(DialogueNode.Action action) {
            option.setOnClickAction(action);
            return this;
        }
    }
}