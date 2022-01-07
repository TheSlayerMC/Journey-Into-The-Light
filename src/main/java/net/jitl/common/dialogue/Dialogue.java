package net.jitl.common.dialogue;

import net.minecraft.resources.ResourceLocation;

public class Dialogue {
    private final ResourceLocation id;
    private final DialogueNode rootNode;

    protected Dialogue(ResourceLocation id, DialogueNode rootNode) {
        this.id = id;
        this.rootNode = rootNode;
    }

    public ResourceLocation getId() {
        return id;
    }

    public DialogueNode getRootNode() {
        return rootNode;
    }
}