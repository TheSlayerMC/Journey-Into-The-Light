package net.jitl.common.dialogue;

import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.dialogue.SCloseDialogueGuiPacket;
import net.jitl.core.network.dialogue.SOpenDialogueGuiPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import ru.timeconqueror.timecore.api.util.NetworkUtils;

import java.util.Optional;
import java.util.UUID;

public class DialogueTracker {
    private final UUID playerId;
    private final EntityType<? extends LivingEntity> entityType;
    private DialoguePage currentNode;

    public DialogueTracker(UUID playerId, EntityType<? extends LivingEntity> entityType, DialoguePage currentNode) {
        this.playerId = playerId;
        this.entityType = entityType;
        this.currentNode = currentNode;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public DialoguePage getCurrentNode() {
        return currentNode;
    }

    public void start() {
        openGuiWithCurrentNode();
    }

    public void pressOption(ServerPlayer player, int optionIndex) throws DialogueSystemException {
        if (optionIndex >= currentNode.getOptions().size()) { // this can be achieved when someone try to use cheaty exploits
            throw new DialogueSystemException("Tracker received the index " + optionIndex + "that doesn't fit options list size (" + currentNode.getOptions().size() + "). Problem node: " + currentNode);
        }

        DialoguePage.Option option = currentNode.getOptions().get(optionIndex);
        option.onClickAction(player.level, player);

        currentNode = option.getNextNode();

        if (currentNode == DialoguePage.END) {
            DialogueManager.getInstance().removeTracker(this);
            closeGui();
        } else {
            openGuiWithCurrentNode();
        }
    }

    private void openGuiWithCurrentNode() {
        getPlayer().ifPresent(player -> JPacketHandler.sendToPlayer(player, new SOpenDialogueGuiPacket(entityType, currentNode)));
    }

    private void closeGui() {
        getPlayer().ifPresent(player -> JPacketHandler.sendToPlayer(player, new SCloseDialogueGuiPacket()));
    }

    private Optional<ServerPlayer> getPlayer() {
        return NetworkUtils.getPlayer(playerId);
    }
}