package net.jitl.common.dialogue;

/*public class DialogueManager {
    private final DialogueNetHandler netHandler = new DialogueNetHandler();
    private final HashMap<UUID, DialogueTracker> trackers = new HashMap<>();//FIXME move to map of lists of trackers

    public void startDialogue(ServerPlayerEntity player, LivingEntity npc, Dialogue dialogue) {
        DialogueRegistry dialogueRegistry = JRegistries.DIALOGUE_REGISTRY;
        dialogueRegistry.verifyRegistration(dialogue);

        startDialogue(player, npc.getClass(), dialogue.getRootNode());
    }

    public DialogueNetHandler getNetHandler() {
        return netHandler;
    }

    private void startDialogue(ServerPlayerEntity player, Class<? extends LivingEntity> npcClass, DialogueNode node) {
        DialogueTracker tracker = new DialogueTracker(player.getUniqueID(), npcClass, node);
        trackers.put(player.getUniqueID(), tracker);

        tracker.start();
    }

    void handleDialogueChosenOption(ServerPlayerEntity player, int optionIndex) throws DialogueSystemException {
        DialogueTracker dialogueTracker = trackers.get(player.getUniqueID());
        if (dialogueTracker == null) { // this can be achieved when someone try to use cheaty exploits
            throw new DialogueSystemException("There are no opened dialogues on server! What are you trying to do???");
        }

        dialogueTracker.pressOption(player, optionIndex);
    }

    void removeTracker(DialogueTracker tracker) {
        trackers.remove(tracker.getPlayerId());
    }
}*/