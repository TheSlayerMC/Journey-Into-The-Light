package net.jitl.common.dialogue;

import net.minecraft.resources.ResourceLocation;

@Deprecated // fixme: for removal
public class DialogueBuilder {
    public static DialoguePage branch(NodeBuilder rootBuilder) {
        return rootBuilder.node;
    }

    public static Dialogue dialogue(ResourceLocation id, NodeBuilder rootNodeBuilder) {
        return dialogue(id, rootNodeBuilder.node);
    }

    public static Dialogue dialogue(ResourceLocation id, DialoguePage rootNode) {
        return new Dialogue(id, rootNode);
    }

    public static NodeBuilder node(String text) {
        return new NodeBuilder(text);
    }

    public static OptionBuilder option(String text) {
        return new OptionBuilder("> " + text);
    }

    public static class NodeBuilder {
        private final DialoguePage node;

        private NodeBuilder(String text) {
            node = new DialoguePage(text, null, "minecraft:sheep", new CharacterMap());
        }

        public NodeBuilder addOption(OptionBuilder builder) {
            node.addOption(builder.option);
            return this;
        }
    }

    public static class OptionBuilder {
        private final DialoguePage.Option option;

        private OptionBuilder(String text) {
            option = new DialoguePage.Option(text);
        }

        public OptionBuilder leadsTo(NodeBuilder nextNode) {
            option.setNextNode(nextNode.node);
            return this;
        }

        public OptionBuilder withAction(DialoguePage.Action action) {
            option.setOnClickAction(action);
            return this;
        }
    }
}