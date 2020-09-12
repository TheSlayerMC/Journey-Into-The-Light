package net.journey.dialogue;

import net.minecraft.entity.EntityLivingBase;

public class Dialogue {
	private final EntityLivingBase npc;
	private final DialogueNode rootNode;
	private final DialogueNode currentNode;

	public Dialogue(EntityLivingBase npc, DialogueNode rootNode) {
		this.npc = npc;
		this.rootNode = rootNode;
		this.currentNode = rootNode;
	}

	public DialogueNode getRootNode() {
		return rootNode;
	}

	public DialogueNode getCurrentNode() {
		return currentNode;
	}

	public EntityLivingBase getNpc() {
		return npc;
	}
}
