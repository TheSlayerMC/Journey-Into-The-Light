package net.jitl.common.dialogue;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;

public class DialogueManager {
    private static DialogueManager instance;

    private final HashMap<UUID, DialogueTracker> trackers = new HashMap<>();//FIXME move to map of lists of trackers

    @SuppressWarnings("unchecked")
    public void startDialogue(ServerPlayer player, LivingEntity npc, Dialogue dialogue) {
//        DialogueRegistry dialogueRegistry = JRegistries.DIALOGUE_REGISTRY;
//        dialogueRegistry.verifyRegistration(dialogue);
        //FIXME add verify

        startDialogue(player, (EntityType<? extends LivingEntity>) npc.getType(), dialogue.getRootNode());
    }

    private void startDialogue(ServerPlayer player, EntityType<? extends LivingEntity> entityType, DialoguePage node) {
        DialogueTracker tracker = new DialogueTracker(player.getUUID(), entityType, node);
        trackers.put(player.getUUID(), tracker);

        tracker.start();
    }

    void handlePressedOption(ServerPlayer player, int optionIndex) throws DialogueSystemException {
        DialogueTracker dialogueTracker = trackers.get(player.getUUID());
        if (dialogueTracker == null) { // this can be achieved when someone try to use cheaty exploits
            throw new DialogueSystemException("There are no opened dialogues on server! What are you trying to do???");
        }

        dialogueTracker.pressOption(player, optionIndex);
    }

    void removeTracker(DialogueTracker tracker) {
        trackers.remove(tracker.getPlayerId());
    }

    public static DialogueManager getInstance() {
        if (instance == null) throw new IllegalStateException("The dialogue manager hasn't been loaded yet.");

        return instance;
    }

    @Mod.EventBusSubscriber
    public static class EventListener {
        @SubscribeEvent
        public void onServerStart(ServerStartingEvent event) {
            instance = new DialogueManager();
        }

        //FIXME remove tracker when player leaves server
    }
}