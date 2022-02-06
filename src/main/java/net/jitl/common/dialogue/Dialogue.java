package net.jitl.common.dialogue;

import net.minecraft.resources.ResourceLocation;

public class Dialogue {
    private final ResourceLocation id;
    private final DialoguePage rootNode;

    protected Dialogue(ResourceLocation id, DialoguePage rootNode) {
        this.id = id;
        this.rootNode = rootNode;
    }

    public ResourceLocation getId() {
        return id;
    }

    public DialoguePage getRootNode() {
        return rootNode;
    }
}