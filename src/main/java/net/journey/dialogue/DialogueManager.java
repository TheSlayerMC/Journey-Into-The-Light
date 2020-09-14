package net.journey.dialogue;

import net.journey.common.JRegistries;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;
import java.util.UUID;

public class DialogueManager {
	private final HashMap<UUID, DialogueTracker> trackers = new HashMap<>();//FIXME move to map of lists of trackers

	public void startDialogue(EntityPlayerMP player, EntityLivingBase npc, Dialogue dialogue) {
		DialogueRegistry dialogueRegistry = JRegistries.DIALOGUE_REGISTRY;
		dialogueRegistry.verifyRegistration(dialogue);

		startDialogue(player, npc.getClass(), dialogue.getRootNode());
	}

	private void startDialogue(EntityPlayerMP player, Class<? extends EntityLivingBase> npcClass, DialogueNode node) {
		DialogueTracker tracker = new DialogueTracker(player.getUniqueID(), npcClass, node);
		trackers.put(player.getUniqueID(), tracker);

		tracker.openGuiWithCurrentNode();
	}

	public void handleDialogueChosenOption(EntityPlayerMP player, int optionIndex) throws DialogueSystemException {
		DialogueTracker dialogueTracker = trackers.get(player.getUniqueID());
		if (dialogueTracker == null) { // this can be achieved when someone try to use cheaty exploits
			throw new DialogueSystemException("There are no opened dialogues on server! What are you trying to do???");
		}

		dialogueTracker.pressOption(player, optionIndex);
	}

	void removeTracker(DialogueTracker tracker) {
		trackers.remove(tracker.getPlayerId());
	}
}
